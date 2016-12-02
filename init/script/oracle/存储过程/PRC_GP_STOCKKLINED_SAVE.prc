CREATE OR REPLACE PROCEDURE PRC_GP_StockKlineD_SAVE
(
stockCode IN VARCHAR2,
periodCode IN VARCHAR2,
priceOpen IN NUMBER,
priceHigh IN NUMBER,
priceLow IN NUMBER,
priceClose IN NUMBER,
tradeVolumn IN NUMBER,
tradeAmount IN NUMBER,
fuquan IN OUT NUMBER,
snapTime IN OUT VARCHAR2,
buy1Count IN NUMBER,
buy1Price IN NUMBER,
buy2Count IN NUMBER,
buy2Price IN NUMBER,
buy3Count IN NUMBER,
buy3Price IN NUMBER,
buy4Count IN NUMBER,
buy4Price IN NUMBER,
buy5Count IN NUMBER,
buy5Price IN NUMBER,
sell1Count IN NUMBER,
sell1Price IN NUMBER,
sell2Count IN NUMBER,
sell2Price IN NUMBER,
sell3Count IN NUMBER,
sell3Price IN NUMBER,
sell4Count IN NUMBER,
sell4Price IN NUMBER,
sell5Count IN NUMBER,
sell5Price IN NUMBER,
originCode IN VARCHAR2
) IS
lastStockKlineD GP_STOCK_KLINE_D%ROWTYPE;
jsq Number;--计数器，临时数据
sequenceNo NUMBER;
closeRef1 NUMBER;
incPer NUMBER;
closeEma5 NUMBER;
closeEma10 NUMBER;
closeEma20 NUMBER;
closeEma30 NUMBER;
closeEma60 NUMBER;
closeEma150 NUMBER;
volumnEma5 NUMBER;
volumnEma10 NUMBER;
volumnEma20 NUMBER;
volumnEma30 NUMBER;
volumnEma60 NUMBER;
volumnEma150 NUMBER;
amountEma5 NUMBER;
amountEma10 NUMBER;
amountEma20 NUMBER;
amountEma30 NUMBER;
amountEma60 NUMBER;
amountEma150 NUMBER;
closeEma6 NUMBER;
closeEma13 NUMBER;
closeEma12 NUMBER;
closeEma26 NUMBER;
dif_S NUMBER;
dea_S NUMBER;
dif_F NUMBER;
dea_F NUMBER;
dea_S_Angle NUMBER;
dea_F_Angle NUMBER;
kdj_K NUMBER;
kdj_D NUMBER;
kdj_J NUMBER;
H9 NUMBER;--9日内最高价 KDJ计算辅助变量
L9 NUMBER;--9日内最低价 KDJ计算辅助变量
RSV NUMBER;--KDJ计算辅助变量
tr NUMBER;
atr NUMBER;
trend NUMBER;
rising NUMBER;
rapidRising NUMBER;
shake NUMBER;
slump NUMBER;
status VARCHAR2(1);
pv_sum_1 NUMBER;
pv_sum_5 NUMBER;
pv_sum_10 NUMBER;
pv_sum_20 NUMBER;
pv_sum_30 NUMBER;
pv_sum_60 NUMBER;
BEGIN
--检查是否已经下载
SELECT COUNT(1) INTO jsq FROM GP_STOCK_KLINE_D WHERE STOCK_CODE = stockCode AND PERIOD_CODE=periodCode AND ORIGIN_CODE='H';
IF jsq IS NULL OR jsq=0 THEN
    --是否有之前的K线
    SELECT COUNT(1) INTO jsq FROM GP_STOCK_KLINE_D 
    WHERE STOCK_CODE = stockCode AND TO_DATE(PERIOD_CODE,'yyyy-mm-dd')<TO_DATE(periodCode,'yyyy-mm-dd');
     
    --计算各技术指标
     IF snapTime IS NULL THEN
       snapTime := TO_CHAR(sysdate,'hh24:mi:ss');
     END IF;
     
     IF jsq>0 THEN--存在之前的一根K线
         --查询当前K线日期之前最近的一根K线
        SELECT * INTO lastStockKlineD
        FROM GP_STOCK_KLINE_D  
        WHERE STOCK_CODE = stockCode 
            AND SEQUENCE_NO = (SELECT MAX(SEQUENCE_NO) 
                            FROM GP_STOCK_KLINE_D 
                            WHERE STOCK_CODE = stockCode
                            AND TO_DATE(PERIOD_CODE,'yyyy-mm-dd')<TO_DATE(periodCode,'yyyy-mm-dd'));
         IF fuquan IS NULL OR fuquan=0 THEN
           fuquan := lastStockKlineD.FUQUAN;
         END IF;
         sequenceNo := lastStockKlineD.SEQUENCE_NO + 1;
         closeRef1 := lastStockKlineD.PRICE_CLOSE;
         closeEma5 := EMA(priceClose,lastStockKlineD.CLOSE_EMA_5,5);
         closeEma10 := EMA(priceClose,lastStockKlineD.CLOSE_EMA_10,10);
         closeEma20 := EMA(priceClose,lastStockKlineD.CLOSE_EMA_20,20);
         closeEma30 := EMA(priceClose,lastStockKlineD.CLOSE_EMA_30,30);
         closeEma60 := EMA(priceClose,lastStockKlineD.CLOSE_EMA_60,60);
         closeEma150 := EMA(priceClose,lastStockKlineD.CLOSE_EMA_150,150);
         volumnEma5 := EMA(tradeVolumn,lastStockKlineD.VOLUMN_EMA_5,5);
         volumnEma10 := EMA(tradeVolumn,lastStockKlineD.VOLUMN_EMA_10,10);
         volumnEma20 := EMA(tradeVolumn,lastStockKlineD.VOLUMN_EMA_20,20);
         volumnEma30 := EMA(tradeVolumn,lastStockKlineD.VOLUMN_EMA_30,30);
         volumnEma60 := EMA(tradeVolumn,lastStockKlineD.VOLUMN_EMA_60,60);
         volumnEma150 := EMA(tradeVolumn,lastStockKlineD.VOLUMN_EMA_150,150);
         amountEma5 := EMA(tradeAmount,lastStockKlineD.AMOUNT_EMA_5,5);
         amountEma10 := EMA(tradeAmount,lastStockKlineD.AMOUNT_EMA_10,10);
         amountEma20 := EMA(tradeAmount,lastStockKlineD.AMOUNT_EMA_20,20);
         amountEma30 := EMA(tradeAmount,lastStockKlineD.AMOUNT_EMA_30,30);
         amountEma60 := EMA(tradeAmount,lastStockKlineD.AMOUNT_EMA_60,60);
         amountEma150 := EMA(tradeAmount,lastStockKlineD.AMOUNT_EMA_150,150);
         closeEma6 := EMA(priceClose,lastStockKlineD.CLOSE_EMA_6,6);
         closeEma13 := EMA(priceClose,lastStockKlineD.CLOSE_EMA_13,13);
         closeEma12 := EMA(priceClose,lastStockKlineD.CLOSE_EMA_12,12);
         closeEma26 := EMA(priceClose,lastStockKlineD.CLOSE_EMA_26,26);
         dif_S := (closeEma12 - closeEma26)*100/closeEma26;
         dif_F := (closeEma6 - closeEma13)*100/closeEma13;
         dea_S := lastStockKlineD.DEA_S*8/10+dif_S*2/10;
         dea_F := lastStockKlineD.DEA_F*3.5/5.5+dif_F*2/5.5;
         dea_S_Angle := ATAN(dea_S-lastStockKlineD.DEA_S)*180/3.1415926;
         dea_F_Angle := ATAN(dea_F-lastStockKlineD.DEA_F)*180/3.1415926;
         SELECT MAX(PRICE_HIGH) INTO H9 FROM GP_STOCK_KLINE_D WHERE STOCK_CODE = stockCode AND SEQUENCE_NO>lastStockKlineD.SEQUENCE_NO-8;
         SELECT MIN(PRICE_LOW) INTO L9 FROM GP_STOCK_KLINE_D WHERE STOCK_CODE = stockCode AND SEQUENCE_NO>lastStockKlineD.SEQUENCE_NO-8;
         IF(priceHigh>H9) THEN
             H9:=priceHigh;
         END IF;
         IF(priceLow<L9) THEN
             L9:=priceLow;
         END IF;
         RSV := (priceClose-L9)/(H9-L9)*100;
         kdj_K := 2*lastStockKlineD.KDJ_K/3+RSV/3;
         kdj_D := 2*lastStockKlineD.KDJ_D/3+kdj_K/3;
         kdj_J := 3*kdj_K-2*kdj_D;
         tr := 100*(priceHigh-priceLow)/lastStockKlineD.PRICE_CLOSE;
         IF 100*ABS(priceHigh-lastStockKlineD.PRICE_CLOSE)/lastStockKlineD.PRICE_CLOSE>tr THEN
           tr :=100*ABS(priceHigh-lastStockKlineD.PRICE_CLOSE)/lastStockKlineD.PRICE_CLOSE;
         END IF;
         IF 100*ABS(priceLow-lastStockKlineD.PRICE_CLOSE)/lastStockKlineD.PRICE_CLOSE>tr THEN
           tr :=100*ABS(priceLow-lastStockKlineD.PRICE_CLOSE)/lastStockKlineD.PRICE_CLOSE;
         END IF;
         atr := 13*lastStockKlineD.ATR/15+2*tr/15;
         incPer := (priceClose-closeRef1)/closeRef1;
         --计算量价强度
         pv_sum_1 := tradeAmount/amountEma150*incPer*100;
         pv_sum_5 := EMA(pv_sum_1,lastStockKlineD.PV_SUM_5,5);
         pv_sum_10 := EMA(pv_sum_1,lastStockKlineD.PV_SUM_10,10);
         pv_sum_20 := EMA(pv_sum_1,lastStockKlineD.PV_SUM_20,20);
         pv_sum_30 := EMA(pv_sum_1,lastStockKlineD.PV_SUM_30,30);
         pv_sum_60 := EMA(pv_sum_1,lastStockKlineD.PV_SUM_60,60);
     ELSE
         IF fuquan IS NULL OR fuquan=0 THEN
           fuquan := 1;
         END IF;
         sequenceNo :=1;
         closeRef1 := priceOpen;
         closeEma5 := priceClose;closeEma10 := priceClose;closeEma20 := priceClose;closeEma30 := priceClose;closeEma60 := priceClose;closeEma150 := priceClose;
         volumnEma5 := tradeVolumn;volumnEma10 := tradeVolumn;volumnEma20 := tradeVolumn;volumnEma30 := tradeVolumn;volumnEma60 := tradeVolumn;volumnEma150 := tradeVolumn;
         amountEma5 := tradeAmount;amountEma10 := tradeAmount;amountEma20 := tradeAmount;amountEma30 := tradeAmount;amountEma60 := tradeAmount;amountEma150 := tradeAmount;
         closeEma6 := priceClose; closeEma13 := priceClose; closeEma12 := priceClose; closeEma26 := priceClose;
         dif_S := (closeEma12 - closeEma26)*100/closeEma26;
         dif_F := (closeEma6 - closeEma13)*100/closeEma13;
         dea_S := dif_S;
         dea_F := dif_F;
         dea_S_Angle := 0;
         dea_F_Angle := 0;
         H9:=priceHigh;
         L9:=priceLow;
         RSV := 50;
         kdj_K := 50;
         kdj_D := 50;
         kdj_J := 3*kdj_K-2*kdj_D;
         tr := 100*(priceHigh-priceLow)/priceClose;
         atr := tr;
         incPer := (priceClose-closeRef1)/closeRef1;
         --计算量价强度
         pv_sum_1 := incPer*100;
         pv_sum_5 := pv_sum_1;
         pv_sum_10 := pv_sum_1;
         pv_sum_20 := pv_sum_1;
         pv_sum_30 := pv_sum_1;
         pv_sum_60 := pv_sum_1;
     END IF;
     
     trend := kdj_K/100*dif_S;
     IF dif_S>dea_S AND kdj_K>kdj_D THEN
       rising := trend;
     ELSE
       rising := 0;
     END IF;
     IF dif_S>dea_S AND kdj_K>kdj_D THEN
       rapidRising := trend;
     ELSE
       rapidRising := 0;
     END IF;
     IF dif_S>dea_S AND kdj_K<=kdj_D THEN
       shake := trend;
     ELSE
       shake := 0;
     END IF;
     IF dif_S<dea_S AND kdj_K<kdj_D AND priceClose<closeEma10 THEN
       slump := trend;
     ELSE
       slump := 0;
     END IF;
     IF trend<0 THEN
       status:='0';
     ELSIF trend>0 AND slump=0 AND (shake>0 OR rapidRising>0) THEN
       status:='1';
     ELSE
       status:='2';
     END IF;
     
     --截取，使精度能满足字段要求
     --tradeVolumn:=round(tradeVolumn);
     --tradeAmount:=round(tradeAmount);
     incPer:=round(incPer,3);
     closeEma5:=round(closeEma5,3);closeEma10:=round(closeEma10,3);closeEma20:=round(closeEma20,3);closeEma30:=round(closeEma30,3);closeEma60:=round(closeEma60,3);closeEma150:=round(closeEma150,3);
     volumnEma5:=round(volumnEma5);volumnEma10:=round(volumnEma10);volumnEma20:=round(volumnEma20);volumnEma30:=round(volumnEma30);volumnEma60:=round(volumnEma60);volumnEma150:=round(volumnEma150);
     amountEma5:=round(amountEma5);amountEma10:=round(amountEma10);amountEma20:=round(amountEma20);amountEma30:=round(amountEma30);amountEma60:=round(amountEma60);amountEma150:=round(amountEma150);
     closeEma6:=round(closeEma6,3);closeEma13:=round(closeEma13,3);closeEma12:=round(closeEma12,3);closeEma26:=round(closeEma26,3);
     dif_S:=round(dif_S,3);dea_S:=round(dea_S,3); dea_S_Angle:=round(dea_S_Angle,3); dif_F:=round(dif_F,3); dea_F:=round(dea_F,3); dea_F_Angle:=round(dea_F_Angle,3);
     kdj_K:=round(kdj_K,3);kdj_D:=round(kdj_D,3);kdj_J:=round(kdj_J,3);tr:=round(tr,3);atr:=round(atr,3);
     trend:=round(trend,3);rising:=round(rising,3);rapidRising:=round(rapidRising,3);shake:=round(shake,3);slump:=round(slump,3);
     --插入或更新K线数据
     SELECT COUNT(1) INTO jsq FROM GP_STOCK_KLINE_D WHERE STOCK_CODE = stockCode AND PERIOD_CODE=periodCode AND ORIGIN_CODE='T';
     IF jsq>0 THEN
     --更新
       UPDATE GP_STOCK_KLINE_D
       SET SNAP_TIME=snapTime,
         PRICE_OPEN=priceOpen,
         PRICE_HIGH=priceHigh,
         PRICE_LOW=priceLow,
         PRICE_CLOSE=priceClose,
         PRICE_CLOSE_REF1=closeRef1,
         TRADE_VOLUMN=tradeVolumn,
         TRADE_AMOUNT=tradeAmount,
         FUQUAN=fuquan,
         AMOUNT_FLOW=0,
         INC_PER=incPer,
         BUY1_COUNT=buy1Count,BUY1_PRICE=buy1Price, BUY2_COUNT=buy2Count,BUY2_PRICE=buy2Price, BUY3_COUNT=buy3Count, 
         BUY3_PRICE=buy3Price,BUY4_COUNT=buy4Count, BUY4_PRICE=buy4Price, BUY5_COUNT=buy5Count,BUY5_PRICE=buy5Price, 
         SELL1_COUNT=sell1Count,SELL1_PRICE=sell1Price,SELL2_COUNT=sell2Count,SELL2_PRICE=sell2Price,SELL3_COUNT=sell3Count,
         SELL3_PRICE=sell3Price,SELL4_COUNT=sell4Count,SELL4_PRICE=sell4Price,SELL5_COUNT=sell5Count,SELL5_PRICE=sell5Price,
         CLOSE_EMA_5=closeEma5, CLOSE_EMA_10=closeEma10,CLOSE_EMA_20=closeEma20,CLOSE_EMA_30=closeEma30,CLOSE_EMA_60=closeEma60,CLOSE_EMA_150=closeEma150,
         VOLUMN_EMA_5=volumnEma5,VOLUMN_EMA_10=volumnEma10,VOLUMN_EMA_20=volumnEma20,VOLUMN_EMA_30=volumnEma30,VOLUMN_EMA_60=volumnEma60,VOLUMN_EMA_150=volumnEma150,
         AMOUNT_EMA_5=amountEma5,AMOUNT_EMA_10=amountEma10,AMOUNT_EMA_20=amountEma20,AMOUNT_EMA_30=amountEma30,AMOUNT_EMA_60=amountEma60,AMOUNT_EMA_150=amountEma150,
         CLOSE_EMA_6=closeEma6,CLOSE_EMA_13=closeEma13,CLOSE_EMA_12=closeEma12,CLOSE_EMA_26=closeEma26,
         DIF_S=dif_S,DEA_S=dea_S,DEA_S_ANGLE=dea_S_Angle,DIF_F=dif_F,DEA_F=dea_F,DEA_F_ANGLE=dea_F_Angle,
         KDJ_K=kdj_K,KDJ_D=kdj_D,KDJ_J=kdj_J,TR=tr,ATR=atr,
         TREND=trend,RISING=rising,RAPID_RISING=rapidRising,SHAKE=shake,SLUMP=slump,STATUS=status,
         PV_SUM_1=pv_sum_1,PV_SUM_5=pv_sum_5,PV_SUM_10=pv_sum_10,PV_SUM_20=pv_sum_20,PV_SUM_30=pv_sum_30,PV_SUM_60=pv_sum_60,
         ORIGIN_CODE=originCode,LAST_UPD_DATE=sysdate,MODIFICATION_NUM=MODIFICATION_NUM+1
       WHERE STOCK_CODE=stockCode AND PERIOD_CODE=periodCode;
     ELSE
     --新增
       INSERT INTO GP_STOCK_KLINE_D(ROW_ID, STOCK_CODE, SEQUENCE_NO,PERIOD_CODE, SNAP_TIME, 
            PRICE_OPEN,PRICE_HIGH, PRICE_LOW, PRICE_CLOSE, 
            PRICE_CLOSE_REF1, TRADE_VOLUMN, TRADE_AMOUNT, 
            FUQUAN, AMOUNT_FLOW, INC_PER, 
            BUY1_COUNT, BUY1_PRICE, BUY2_COUNT,BUY2_PRICE, BUY3_COUNT, BUY3_PRICE,BUY4_COUNT, BUY4_PRICE, BUY5_COUNT,BUY5_PRICE, 
            SELL1_COUNT,SELL1_PRICE,SELL2_COUNT,SELL2_PRICE,SELL3_COUNT,SELL3_PRICE,SELL4_COUNT,SELL4_PRICE,SELL5_COUNT,SELL5_PRICE, 
            CLOSE_EMA_5,CLOSE_EMA_10, CLOSE_EMA_20, CLOSE_EMA_30, 
            CLOSE_EMA_60, CLOSE_EMA_150, VOLUMN_EMA_5, 
            VOLUMN_EMA_10, VOLUMN_EMA_20, VOLUMN_EMA_30, 
            VOLUMN_EMA_60, VOLUMN_EMA_150, AMOUNT_EMA_5, 
            AMOUNT_EMA_10, AMOUNT_EMA_20, AMOUNT_EMA_30, 
            AMOUNT_EMA_60, AMOUNT_EMA_150, CLOSE_EMA_6, 
            CLOSE_EMA_13, CLOSE_EMA_12, CLOSE_EMA_26, 
            DIF_S, DEA_S, DEA_S_ANGLE, 
            DIF_F, DEA_F, DEA_F_ANGLE, 
            KDJ_K, KDJ_D, KDJ_J, TR, ATR,
            TREND, RISING, RAPID_RISING, 
            SHAKE, SLUMP, STATUS, 
            PV_SUM_1, PV_SUM_5, PV_SUM_10, PV_SUM_20, PV_SUM_30,PV_SUM_60, RANKING, 
            DELETED_FLAG, ORIGIN_CODE, CREATED_DATE, 
            LAST_UPD_DATE, MODIFICATION_NUM)
       VALUES(SEQ_ROW_ID.nextval,stockCode,sequenceNo,periodCode,snapTime,
              priceOpen,priceHigh,priceLow,priceClose,closeRef1,tradeVolumn,tradeAmount,fuquan,0,incPer,
              buy1Count,buy1Price,buy2Count,buy2Price,buy3Count,buy3Price,buy4Count,buy4Price,buy5Count,buy5Price,
              sell1Count,sell1Price,sell2Count,sell2Price,sell3Count,sell3Price,sell4Count,sell4Price,sell5Count,sell5Price,
              closeEma5,closeEma10,closeEma20,closeEma30,closeEma60,closeEma150,
              volumnEma5,volumnEma10,volumnEma20,volumnEma30,volumnEma60,volumnEma150,
              amountEma5,amountEma10,amountEma20,amountEma30,amountEma60,amountEma150,
              closeEma6,closeEma13,closeEma12,closeEma26,dif_S,dea_S,dea_S_Angle,dif_F,dea_F,dea_F_Angle,
              kdj_K,kdj_D,kdj_J,tr,atr,trend,rising,rapidRising,shake,slump,status,pv_sum_1,pv_sum_5,pv_sum_10,pv_sum_20,pv_sum_30,pv_sum_60,0,
              '0',originCode,sysdate,sysdate,0);
     END IF;

END IF;--判断是否已经下载了此K线
     
END PRC_GP_StockKlineD_SAVE;
/
