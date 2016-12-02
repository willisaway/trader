CREATE OR REPLACE PROCEDURE PRC_GP_StockKlineW_DOWNLOAD
(
objectCode IN VARCHAR2,
periodCode IN VARCHAR2,
priceOpen IN NUMBER,
priceHigh IN NUMBER,
priceLow IN NUMBER,
priceClose IN NUMBER,
tradeVolumn IN NUMBER,
tradeAmount IN NUMBER,
fuquan IN OUT NUMBER,
originCode IN VARCHAR2
) IS
snapTime VARCHAR2(32);
BEGIN
    --获取当前时间
     snapTime := TO_CHAR(sysdate,'yyyy-mm-dd hh24:mi:ss');
     PRC_GP_StockKlineW_SAVE(objectCode,periodCode,priceOpen,priceHigh,priceLow,priceClose,tradeVolumn,tradeAmount,fuquan,snapTime,originCode);
     
END PRC_GP_StockKlineW_DOWNLOAD;
/