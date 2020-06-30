package com.example.drn20;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class LoginValidatorUPS {


    /// Soap XML for UPS , this class validates entry and get the SessionID for other methods
    private static String NAME_SPACE = "http://ws.ups.com.tr/wsPaketIslemSorgulamaEng/";
    private static String METHOD_NAME = "Login_V1";
    private static String SOAP_ACTION = "http://ws.ups.com.tr/wsPaketIslemSorgulamaEng/Login_V1";
    private static String URL = "http://ws.ups.com.tr/QueryPackageInfo/wsQueryPackagesInfo.asmx";
    //////////

    String IdSession;


    public static VerifyLogin verifyLogin(String CustomerNumber, String UserName, String Password) {
        VerifyLogin object = new VerifyLogin();

        SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
        request.addProperty("CustomerNumber","E7E097");
        request.addProperty("UserName", "nYPpJMFQenld61kMPJYT");
        request.addProperty("Password","aFkaHQKcBPb7lubl4UL6");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.debug = true;


        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject response = (SoapObject) envelope.getResponse();

            if (response.hasProperty("SessionID")) {
                if (response.getProperty("SessionID") == null) {
                    object.setSessionID(null);
                } else {
                    object.setSessionID(response.getProperty("SessionID").toString());
                }
            }

            if (response.hasProperty("ErrorCode")) {
                if (response.getProperty("ErrorCode") == null) {
                    object.setErrorCode(-1);
                } else {
                    object.setErrorCode((int) response.getProperty("ErrorCode"));
                }
            }

            if (response.hasProperty("ErrorDefinition")) {
                if (response.getProperty("ErrorDefinition") == null) {
                    object.setErrorDefiniton(null);
                } else {
                    object.setErrorDefiniton(response.getProperty("ErrorDefinition").toString());
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;

    }


}
