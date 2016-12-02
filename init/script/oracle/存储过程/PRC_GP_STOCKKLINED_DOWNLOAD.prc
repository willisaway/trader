CREATE OR REPLACE PROCEDURE PRC_GP_StockKlineD_DOWNLOAD
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
     snapTime := TO_CHAR(sysdate,'hh24:mi:ss');
     PRC_GP_StockKlineD_SAVE(objectCode,periodCode,priceOpen,priceHigh,priceLow,priceClose,tradeVolumn,tradeAmount,fuquan,snapTime,
     0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,originCode);
     
END PRC_GP_StockKlineD_DOWNLOAD;
/