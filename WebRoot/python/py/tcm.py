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
    cols = [u'fei', u'xin', u'piwei', u'gandan', u'shen',
       u'sanjiao', u'qi', u'xue', u'yin', u'yang', u'xing', u'ku', u'gan',
       u'suan', u'xian', u'dan', u'sheng', u'jiang', u'chen', u'fu',
       u'hezhong']
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
