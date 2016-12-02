CREATE OR REPLACE PROCEDURE PRC_GP_ClassKline30F_SAVE
(
classifyCode IN VARCHAR2,
periodCode IN VARCHAR2,
priceOpen IN NUMBER,
priceHigh IN NUMBER,
priceLow IN NUMBER,
priceClose IN NUMBER,
tradeVolumn IN NUMBER,
tradeAmount IN NUMBER,
snapTime IN OUT VARCHAR2,
originCode IN VARCHAR2
) IS
lastClassKline30F GP_CLASS_KLINE_30F%ROWTYPE;
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
SELECT COUNT(1) INTO jsq FROM GP_CLASS_KLINE_30F WHERE CLASSIFY_CODE = classifyCode AND PERIOD_CODE=periodCode AND ORIGIN_CODE='H';
IF jsq IS NULL OR jsq=0 THEN
    --是否有之前的K线
    SELECT COUNT(1) INTO jsq FROM GP_CLASS_KLINE_30F 
    WHERE CLASSIFY_CODE = classifyCode AND TO_DATE(PERIOD_CODE,'yyyy-mm-dd hh24:mi:ss')<TO_DATE(periodCode,'yyyy-mm-dd hh24:mi:ss');
     
    --计算各技术指标
     IF snapTime IS NULL THEN
       snapTime := TO_CHAR(sysdate,'hh24:mi:ss');
     END IF;
     
     IF jsq>0 THEN--存在之前的一根K线
         --查询当前K线日期之前最近的一根K线
        SELECT * INTO lastClassKline30F
        FROM GP_CLASS_KLINE_30F  
        WHERE CLASSIFY_CODE = classifyCode 
            AND SEQUENCE_NO = (SELECT MAX(SEQUENCE_NO) 
                            FROM GP_CLASS_KLINE_30F 
                            WHERE CLASSIFY_CODE = classifyCode
                            AND TO_DATE(PERIOD_CODE,'yyyy-mm-dd hh24:mi:ss')<TO_DATE(periodCode,'yyyy-mm-dd hh24:mi:ss'));
         sequenceNo := lastClassKline30F.SEQUENCE_NO + 1;
         closeRef1 := lastClassKline30F.PRICE_CLOSE;
         closeEma5 := EMA(priceClose,lastClassKline30F.CLOSE_EMA_5,5);
         closeEma10 := EMA(priceClose,lastClassKline30F.CLOSE_EMA_10,10);
         closeEma20 := EMA(priceClose,lastClassKline30F.CLOSE_EMA_20,20);
         closeEma30 := EMA(priceClose,lastClassKline30F.CLOSE_EMA_30,30);
         closeEma60 := EMA(priceClose,lastClassKline30F.CLOSE_EMA_60,60);
         closeEma150 := EMA(priceClose,lastClassKline30F.CLOSE_EMA_150,150);
         volumnEma5 := EMA(tradeVolumn,lastClassKline30F.VOLUMN_EMA_5,5);
         volumnEma10 := EMA(tradeVolumn,lastClassKline30F.VOLUMN_EMA_10,10);
         volumnEma20 := EMA(tradeVolumn,lastClassKline30F.VOLUMN_EMA_20,20);
         volumnEma30 := EMA(tradeVolumn,lastClassKline30F.VOLUMN_EMA_30,30);
         volumnEma60 := EMA(tradeVolumn,lastClassKline30F.VOLUMN_EMA_60,60);
         volumnEma150 := EMA(tradeVolumn,lastClassKline30F.VOLUMN_EMA_150,150);
         amountEma5 := EMA(tradeAmount,lastClassKline30F.AMOUNT_EMA_5,5);
         amountEma10 := EMA(tradeAmount,lastClassKline30F.AMOUNT_EMA_10,10);
         amountEma20 := EMA(tradeAmount,lastClassKline30F.AMOUNT_EMA_20,20);
         amountEma30 := EMA(tradeAmount,lastClassKline30F.AMOUNT_EMA_30,30);
         amountEma60 := EMA(tradeAmount,lastClassKline30F.AMOUNT_EMA_60,60);
         amountEma150 := EMA(tradeAmount,lastClassKline30F.AMOUNT_EMA_150,150);
         closeEma6 := EMA(priceClose,lastClassKline30F.CLOSE_EMA_6,6);
         closeEma13 := EMA(priceClose,lastClassKline30F.CLOSE_EMA_13,13);
         closeEma12 := EMA(priceClose,lastClassKline30F.CLOSE_EMA_12,12);
         closeEma26 := EMA(priceClose,lastClassKline30F.CLOSE_EMA_26,26);
         dif_S := (closeEma12 - closeEma26)*10000/closeEma26;
         dif_F := (closeEma6 - closeEma13)*10000/closeEma13;
         dea_S := lastClassKline30F.DEA_S*8/10+dif_S*2/10;
         dea_F := lastClassKline30F.DEA_F*3.5/5.5+dif_F*2/5.5;
         dea_S_Angle := ATAN(dea_S-lastClassKline30F.DEA_S)*180/3.1415926;
         dea_F_Angle := ATAN(dea_F-lastClassKline30F.DEA_F)*180/3.1415926;
         SELECT MAX(PRICE_HIGH) INTO H9 FROM GP_CLASS_KLINE_30F WHERE CLASSIFY_CODE = classifyCode AND SEQUENCE_NO>lastClassKline30F.SEQUENCE_NO-8;
         SELECT MIN(PRICE_LOW) INTO L9 FROM GP_CLASS_KLINE_30F WHERE CLASSIFY_CODE = classifyCode AND SEQUENCE_NO>lastClassKline30F.SEQUENCE_NO-8;
         IF(priceHigh>H9) THEN
             H9:=priceHigh;
         END IF;
         IF(priceLow<L9) THEN
             L9:=priceLow;
         END IF;
         RSV := (priceClose-L9)/(H9-L9)*100;
         kdj_K := 2*lastClassKline30F.KDJ_K/3+RSV/3;
         kdj_D := 2*lastClassKline30F.KDJ_D/3+kdj_K/3;
         kdj_J := 3*kdj_K-2*kdj_D;
         tr := 100*(priceHigh-priceLow)/lastClassKline30F.PRICE_CLOSE;
         IF 100*ABS(priceHigh-lastClassKline30F.PRICE_CLOSE)/lastClassKline30F.PRICE_CLOSE>tr THEN
           tr :=100*ABS(priceHigh-lastClassKline30F.PRICE_CLOSE)/lastClassKline30F.PRICE_CLOSE;
         END IF;
         IF 100*ABS(priceLow-lastClassKline30F.PRICE_CLOSE)/lastClassKline30F.PRICE_CLOSE>tr THEN
           tr :=100*ABS(priceLow-lastClassKline30F.PRICE_CLOSE)/lastClassKline30F.PRICE_CLOSE;
         END IF;
         atr := 13*lastClassKline30F.ATR/15+2*tr/15;
         incPer := (priceClose-closeRef1)/closeRef1;
         --计算量价强度
         pv_sum_1 := tradeAmount/amountEma150*incPer*100;
         pv_sum_5 := EMA(pv_sum_1,lastClassKline30F.PV_SUM_5,5);
         pv_sum_10 := EMA(pv_sum_1,lastClassKline30F.PV_SUM_10,10);
         pv_sum_20 := EMA(pv_sum_1,lastClassKline30F.PV_SUM_20,20);
         pv_sum_30 := EMA(pv_sum_1,lastClassKline30F.PV_SUM_30,30);
         pv_sum_60 := EMA(pv_sum_1,lastClassKline30F.PV_SUM_60,60);
     ELSE
         sequenceNo :=1;
         closeRef1 := priceOpen;
         closeEma5 := priceClose;closeEma10 := priceClose;closeEma20 := priceClose;closeEma30 := priceClose;closeEma60 := priceClose;closeEma150 := priceClose;
         volumnEma5 := tradeVolumn;volumnEma10 := tradeVolumn;volumnEma20 := tradeVolumn;volumnEma30 := tradeVolumn;volumnEma60 := tradeVolumn;volumnEma150 := tradeVolumn;
         amountEma5 := tradeAmount;amountEma10 := tradeAmount;amountEma20 := tradeAmount;amountEma30 := tradeAmount;amountEma60 := tradeAmount;amountEma150 := tradeAmount;
         closeEma6 := priceClose; closeEma13 := priceClose; closeEma12 := priceClose; closeEma26 := priceClose;
         dif_S := (closeEma12 - closeEma26)*10000/closeEma26;
         dif_F := (closeEma6 - closeEma13)*10000/closeEma13;
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
     SELECT COUNT(1) INTO jsq FROM GP_CLASS_KLINE_30F WHERE CLASSIFY_CODE = classifyCode AND PERIOD_CODE=periodCode AND ORIGIN_CODE='T';
     IF jsq>0 THEN
     --更新
       UPDATE GP_CLASS_KLINE_30F
       SET SNAP_TIME=snapTime,
         PRICE_OPEN=priceOpen,
         PRICE_HIGH=priceHigh,
         PRICE_LOW=priceLow,
         PRICE_CLOSE=priceClose,
         PRICE_CLOSE_REF1=closeRef1,
         TRADE_VOLUMN=tradeVolumn,
         TRADE_AMOUNT=tradeAmount,
         INC_PER=incPer,
         CLOSE_EMA_5=closeEma5, CLOSE_EMA_10=closeEma10,CLOSE_EMA_20=closeEma20,CLOSE_EMA_30=closeEma30,CLOSE_EMA_60=closeEma60,CLOSE_EMA_150=closeEma150,
         VOLUMN_EMA_5=volumnEma5,VOLUMN_EMA_10=volumnEma10,VOLUMN_EMA_20=volumnEma20,VOLUMN_EMA_30=volumnEma30,VOLUMN_EMA_60=volumnEma60,VOLUMN_EMA_150=volumnEma150,
         AMOUNT_EMA_5=amountEma5,AMOUNT_EMA_10=amountEma10,AMOUNT_EMA_20=amountEma20,AMOUNT_EMA_30=amountEma30,AMOUNT_EMA_60=amountEma60,AMOUNT_EMA_150=amountEma150,
         CLOSE_EMA_6=closeEma6,CLOSE_EMA_13=closeEma13,CLOSE_EMA_12=closeEma12,CLOSE_EMA_26=closeEma26,
         DIF_S=dif_S,DEA_S=dea_S,DEA_S_ANGLE=dea_S_Angle,DIF_F=dif_F,DEA_F=dea_F,DEA_F_ANGLE=dea_F_Angle,
         KDJ_K=kdj_K,KDJ_D=kdj_D,KDJ_J=kdj_J,TR=tr,ATR=atr,
         TREND=trend,RISING=rising,RAPID_RISING=rapidRising,SHAKE=shake,SLUMP=slump,STATUS=status,
         PV_SUM_1=pv_sum_1,PV_SUM_5=pv_sum_5,PV_SUM_10=pv_sum_10,PV_SUM_20=pv_sum_20,PV_SUM_30=pv_sum_30,PV_SUM_60=pv_sum_60,
         ORIGIN_CODE='H',LAST_UPD_DATE=sysdate,MODIFICATION_NUM=MODIFICATION_NUM+1
       WHERE CLASSIFY_CODE=classifyCode AND PERIOD_CODE=periodCode;
     ELSE
     --新增
       INSERT INTO GP_CLASS_KLINE_30F(ROW_ID, CLASSIFY_CODE, SEQUENCE_NO,PERIOD_CODE, SNAP_TIME, 
            PRICE_OPEN,PRICE_HIGH, PRICE_LOW, PRICE_CLOSE, 
            PRICE_CLOSE_REF1, TRADE_VOLUMN, TRADE_AMOUNT, 
            INC_PER, 
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
       VALUES(SEQ_ROW_ID.nextval,classifyCode,sequenceNo,periodCode,snapTime,
              priceOpen,priceHigh,priceLow,priceClose,closeRef1,tradeVolumn,tradeAmount,incPer,
              closeEma5,closeEma10,closeEma20,closeEma30,closeEma60,closeEma150,
              volumnEma5,volumnEma10,volumnEma20,volumnEma30,volumnEma60,volumnEma150,
              amountEma5,amountEma10,amountEma20,amountEma30,amountEma60,amountEma150,
              closeEma6,closeEma13,closeEma12,closeEma26,dif_S,dea_S,dea_S_Angle,dif_F,dea_F,dea_F_Angle,
              kdj_K,kdj_D,kdj_J,tr,atr,trend,rising,rapidRising,shake,slump,status,pv_sum_1,pv_sum_5,pv_sum_10,pv_sum_20,pv_sum_30,pv_sum_60,0,
              '0',originCode,sysdate,sysdate,0);
     END IF;

END IF;--判断是否已经下载了此K线
     
END PRC_GP_ClassKline30F_SAVE;
/
