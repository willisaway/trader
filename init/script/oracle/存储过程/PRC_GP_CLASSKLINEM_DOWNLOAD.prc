CREATE OR REPLACE PROCEDURE PRC_GP_ClassKlineM_DOWNLOAD
(
objectCode IN VARCHAR2,
periodCode IN VARCHAR2,
priceOpen IN NUMBER,
priceHigh IN NUMBER,
priceLow IN NUMBER,
priceClose IN NUMBER,
tradeVolumn IN NUMBER,
tradeAmount IN NUMBER,
originCode IN VARCHAR2
) IS
snapTime VARCHAR2(32);
BEGIN
    --获取当前时间
     snapTime := TO_CHAR(sysdate,'yyyy-mm-dd hh24:mi:ss');
     PRC_GP_ClassKlineM_SAVE(objectCode,periodCode,priceOpen,priceHigh,priceLow,priceClose,tradeVolumn,tradeAmount,snapTime,originCode);
     
END PRC_GP_ClassKlineM_DOWNLOAD;
/