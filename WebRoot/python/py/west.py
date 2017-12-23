# coding=utf-8
import pandas as pd
import numpy as np
from sklearn.externals import joblib
import sys 


def writeFile(aucValue):
    f=open('/Users/zhuqiuhui/zqh/code/foodAnyalis/model/hypertension/res.txt','a')
    f.write(str(aucValue))
    f.close()

def modelPredict(modelPath, parameter):
    test = convertParameter(parameter)
    xgb_model = joblib.load(modelPath)
    y_pred_proba = xgb_model.predict_proba(test)[:, 1]
    return y_pred_proba[0]

def convertParameter(parameter):
    parameterStr = parameter.split(',')
    # test = {u'calories':[float(parameterStr[0])],
    #         u'carbohydrate':[float(parameterStr[1])],
    #         u'fat':[float(parameterStr[2])],
    #         u'protein':[float(parameterStr[3])],
    #         u'vitamine':[float(parameterStr[4])],
    #         u'vta':[float(parameterStr[5])],
    #         u'vtc':[float(parameterStr[6])],
    #         u'vte':[float(parameterStr[7])],
    #         u'carotene':[float(parameterStr[8])],
    #         u'thiamine':[float(parameterStr[9])],
    #         u'riboflavin':[float(parameterStr[10])],
    #         u'yansuan':[float(parameterStr[11])],
    #         u'cholesterol':[float(parameterStr[12])],
    #         u'mg':[float(parameterStr[13])],
    #         u'ca':[float(parameterStr[14])],
    #         u'iron':[float(parameterStr[15])],
    #         u'zinc':[float(parameterStr[16])],
    #         u'copper':[float(parameterStr[17])],
    #         u'mn':[float(parameterStr[18])],
    #         u'k':[float(parameterStr[19])],
    #         u'p':[float(parameterStr[20])],
    #         u'na':[float(parameterStr[21])],
    #         u'se':[float(parameterStr[22])]
    #         }
    cols = [u'calories', u'carbohydrate', u'fat', u'protein', u'vitamine', u'vta',
       u'vtc', u'vte', u'carotene', u'thiamine', u'riboflavin', u'yansuan',
       u'cholesterol', u'mg', u'ca', u'iron', u'zinc', u'copper', u'mn', u'k',
       u'p', u'na', u'se']
    testList = []
    for str in parameterStr:
        testList.append(float(str))
    return pd.DataFrame(data=np.array([testList]), columns=cols)


if __name__ == '__main__':
    modelPath = sys.argv[1]
    parameter = sys.argv[2]
    # testStr = "309.58,66.72,2.12,8.02,2.15,19.97,47.03,3.88,118.71,0.24,0.09,1.35,0.0,90.71,48.0,4.38,1.74,0.43,0.78,354.52,182.42,4.4,3.85"
    aucValue = modelPredict(str(modelPath), str(parameter))
    print(aucValue)
