package com.example.drn20;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class TrackingUPS {

    private static String NAME_SPACE1 = "http://ws.ups.com.tr/wsPaketIslemSorgulamaEng/";
    private static String METHOD_NAME1 = "GetTransactionsByTrackingNumber_V1";
    private static String SOAP_ACTION1 = "http://ws.ups.com.tr/wsPaketIslemSorgulamaEng/GetTransactionsByTrackingNumber_V1";
    private static String URL1 = "http://ws.ups.com.tr/QueryPackageInfo/wsQueryPackagesInfo.asmx";



    public static TrackCargo trackCargo(String SessionID , int InformationLevel , String TrackingNumber){
        TrackCargo object2 = new TrackCargo();

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
            //response1 = androidHttpTransport.responseDump;
            //SoapObject soapActionList = (SoapObject) response1.getProperty("PackageTransaction");
           // int ActionCount = soapActionList.getPropertyCount();


             //   TrackCargo trackCargo = new TrackCargo();



            object2.setTrackingNumber(response1.toString());




                /*if (soapAction.hasProperty("ProcessTimeStamp")) {
                    object2.setProcessTimeStamp(soapAction.getProperty("ProcessTimeStamp").toString());
                }

                if (soapAction.hasProperty("OperationBranchName")) {
                    object2.setOperationBranchName(soapAction.getProperty("OperationBranchName").toString());
                }

                if (soapAction.hasProperty("StatusCode")) {
                    object2.setStatusCode((int)soapAction.getProperty("StatusCode"));
                }

                if (soapAction.hasProperty("ExceptionCode")) {
                    object2.setExceptionCode(soapAction.getProperty("ExceptionCode").toString());
                }

                if (soapAction.hasProperty("ProcessDescription1")) {
                    object2.setProcessDescription1(soapAction.getProperty("ProcessDescription1").toString());
                }

                if (soapAction.hasProperty("ProcessDescription2")) {
                    object2.setProcessDescription2(soapAction.getProperty("ProcessDescription2").toString());
                }

                if (soapAction.hasProperty("RecordId")) {
                    object2.setRecordId((long)soapAction.getProperty("RecordId"));
                }

                if (soapAction.hasProperty("InformationLevel")) {
                    object2.setInformationLevel((int)soapAction.getProperty("InformationLevel"));
                }

                if (soapAction.hasProperty("ErrorCode")) {
                    object2.setErrorCode((int)soapAction.getProperty("ErrorCode"));
                }

                if (soapAction.hasProperty("ErrorDefinition")) {
                    object2.setErrorDefiniton(soapAction.getProperty("ErrorDefinition").toString());
                }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return object2;

    }

}
