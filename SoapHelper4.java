package com.example.drn20;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class SoapHelper4 {

    private static String NAME_SPACE1 = "http://ws.ups.com.tr/wsPaketIslemSorgulamaEng/";
    private static String METHOD_NAME1 = "GetTransactionsByTrackingNumber_V1";
    private static String SOAP_ACTION1 = "http://ws.ups.com.tr/wsPaketIslemSorgulamaEng/GetTransactionsByTrackingNumber_V1";
    private static String URL1 = "http://ws.ups.com.tr/QueryPackageInfo/wsQueryPackagesInfo.asmx";


    public ArrayList<String> getSoapRequest(String SessionID , int InformationLevel , String TrackingNumber){
        ArrayList<String> myList = new ArrayList<>();

        SoapObject request1 = new SoapObject(NAME_SPACE1, METHOD_NAME1);
        request1.addProperty("SessionID",SessionID);
        request1.addProperty("InformationLevel", InformationLevel);
        request1.addProperty("TrackingNumber",TrackingNumber);

        SoapSerializationEnvelope envelope1 = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope1.dotNet = true;
        envelope1.setOutputSoapObject(request1);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL1);
        androidHttpTransport.debug = true;
        androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");

        try {
            androidHttpTransport.call(SOAP_ACTION1, envelope1);
            SoapObject response1 = (SoapObject) envelope1.getResponse();

            for(int i=0;i<response1.getPropertyCount();i++) {
                Object property = response1.getProperty(i);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
//                       myNameList[i] = final_object.getProperty("");
                    /*TrackCargo item = new TrackCargo();
                    item.setTrackingNumber(final_object.getProperty("TrackingNumber").toString());
                    item.setProcessTimeStamp(final_object.getProperty("ProcessTimeStamp").toString());
                    item.setOperationBranchName(final_object.getProperty("OperationBranchName").toString());
                    item.setStatusCode((int)final_object.getProperty("StatusCode"));
                    item.setExceptionCode(final_object.getProperty("ExceptionCode").toString());
                    item.setProcessDescription1(final_object.getProperty("ProcessDescription1").toString());
                    item.setProcessDescription2(final_object.getProperty("ProcessDescription2").toString());
                    item.setRecordId((long)final_object.getProperty("RecordId"));
                    item.setErrorCode((int)final_object.getProperty("ErrorCode"));
                    item.setProcessTimeStamp(final_object.getProperty("ErrorDefinition").toString());
                    myList.add(item);*/
                    //myList.add(final_object.getProperty("TrackingNumber").toString());
                    //myList.add(final_object.getProperty("ProcessTimeStamp").toString());
                    //myList.add(final_object.getProperty("OperationBranchName").toString());
                    // myList.add(final_object.getProperty("StatusCode").toString());
                    // myList.add(final_object.getProperty("ExceptionCode").toString());
                     myList.add(final_object.getProperty("ProcessDescription1").toString());
                    //  myList.add(final_object.getProperty("ProcessDescription2").toString());
                    // myList.add(final_object.getProperty("RecordId").toString());
                    //myList.add(final_object.getProperty("ErrorCode").toString());
                    //myList.add(final_object.getProperty("ErrorDefinition").toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myList;
    }
}
