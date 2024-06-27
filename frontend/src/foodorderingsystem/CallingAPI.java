package foodorderingsystem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class CallingAPI {
    private JSONObject jsonResponse;
    
    public static void main (String[] args) {
		//new CallingAPI().readCustomerAPI("Angelina", "pass789");
		//new CallingAPI().readStaffAPI("Vincent Tan", "staff123");
		//new CallingAPI().addCustomerOrderAPI("2024-06-22 11:06:30", "eat in", "12", "Pending", "13.40", "C00002");
		//new CallingAPI().readCustomerOrderAPI("Pending");
    	//new CallingAPI().readPersonalCustomerOrder("C00001");
		//new CallingAPI().updateCustomerOrderAPI("O00002", "In progress", "S00001");
		//new CallingAPI().deleteCustomerOrderAPI("O00003");
		//new CallingAPI().readMealAPI();
		//new CallingAPI().addOrderMealAPI("O00002", "M00001", "2");
		//new CallingAPI().readOrderMealAPI("O00035");
		//new CallingAPI().deleteOrderMealAPI("O00002");
		//new CallingAPI().addPaymentAPI("P00001", "13.40", "2024-06-22 11:07:15", "e-wallet", "O00001", "S00002");
		//new CallingAPI().readPaymentAPI("O00002");
		//new CallingAPI().deletePayment("P00001");
	}
    
    JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params) {
        InputStream is = null;
        String json = "";
        JSONObject jObj = null;
        
        // Making HTTP request
        try {
            // check for request method
            if (method.equals("POST")) {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            } else if (method.equals("GET")) {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            } else if (method.equals("PUT")) {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPut httpPut = new HttpPut(url);
                httpPut.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
                HttpResponse httpResponse = httpClient.execute(httpPut);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            } else if (method.equals("DELETE")) {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpDelete httpDelete = new HttpDelete(url);
                HttpResponse httpResponse = httpClient.execute(httpDelete);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            jObj = new JSONObject(json);
            jsonResponse = jObj; // Save the response to jsonResponse
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return jObj;
    }
    
    public JSONObject getJsonResponse() {
        return jsonResponse;
    }
    
    public void readCustomerAPI(String customerName, String password) {
        String url = "https://hushed-charming-clipper.glitch.me/customer";
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("customerName", customerName));
        params.add(new BasicNameValuePair("password", password));
        
        makeHttpRequest(url, "GET", params);
    }
    
    public void readStaffAPI(String staffName, String password) {
        String url = "https://hushed-charming-clipper.glitch.me/staff";
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("staffName", staffName));
        params.add(new BasicNameValuePair("password", password));
        
        makeHttpRequest(url, "GET", params);
    }
    
    public void addCustomerOrderAPI(String OrderDateTime, String OrderType, 
            String TableNo, String Status, String TotalPrice, String CustomerID) {
        String url = "https://hushed-charming-clipper.glitch.me/customer_order";
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("OrderDateTime", OrderDateTime));
        params.add(new BasicNameValuePair("OrderType", OrderType));
        params.add(new BasicNameValuePair("TableNo", TableNo));
        params.add(new BasicNameValuePair("Status", Status));
        params.add(new BasicNameValuePair("TotalPrice", TotalPrice));
        params.add(new BasicNameValuePair("CustomerID", CustomerID));
        
        makeHttpRequest(url, "POST", params);
    }
    
    // used by staff
    public void readCustomerOrderAPI(String Status) {
        String url = "https://hushed-charming-clipper.glitch.me/customer_order";
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Status", Status));
        
        makeHttpRequest(url, "GET", params);
    }
    
    // used by customer
    public void readPersonalCustomerOrder(String CustomerID) {
        String url = "https://hushed-charming-clipper.glitch.me/customer_order/personal";
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("CustomerID", CustomerID));
        
        System.out.println( makeHttpRequest(url, "GET", params));
    }
    
    public void updateCustomerOrderAPI(String OrderID, String Status, String StaffID) {
        String url = "https://hushed-charming-clipper.glitch.me/customer_order";
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("OrderID", OrderID));
        params.add(new BasicNameValuePair("Status", Status));
        params.add(new BasicNameValuePair("StaffID", StaffID));
        
        makeHttpRequest(url, "PUT", params);
    }
    
    public void deleteCustomerOrderAPI(String OrderID) {
        String url = "https://hushed-charming-clipper.glitch.me/customer_order";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("OrderID", OrderID));
        
        makeHttpRequest(url, "DELETE", params);
    }
    
    public void readMealAPI() {
        String url = "https://hushed-charming-clipper.glitch.me/meal";
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        
        makeHttpRequest(url, "GET", params);
    }
    
    public void addOrderMealAPI(String OrderID, String MealID, String Quantity) {
        String url = "https://hushed-charming-clipper.glitch.me/order_meal";
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("OrderID", OrderID));
        params.add(new BasicNameValuePair("MealID", MealID));
        params.add(new BasicNameValuePair("Quantity", Quantity));
        
        makeHttpRequest(url, "POST", params);
    }
    
    public void readOrderMealAPI(String OrderID) {
        String url = "https://hushed-charming-clipper.glitch.me/order_meal";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("OrderID", OrderID));

        //makeHttpRequest(url, "GET", params);
        System.out.println(makeHttpRequest(url, "GET", params));
    }

    
    public void deleteOrderMealAPI(String OrderID) {
        String url = "https://hushed-charming-clipper.glitch.me/order_meal";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("OrderID", OrderID));
        
        makeHttpRequest(url, "DELETE", params);
    }
    
    public void addPaymentAPI(String PaymentID, String TotalPayment, String PaymentDateTime, 
            String PaymentType, String OrderID, String StaffID) {
        String url = "https://hushed-charming-clipper.glitch.me/payment";
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("PaymentID", PaymentID));
        params.add(new BasicNameValuePair("TotalPayment", TotalPayment));
        params.add(new BasicNameValuePair("PaymentDateTime", PaymentDateTime));
        params.add(new BasicNameValuePair("PaymentType", PaymentType));
        params.add(new BasicNameValuePair("OrderID", OrderID));
        params.add(new BasicNameValuePair("StaffID", StaffID));
        
        makeHttpRequest(url, "POST", params);
    }
    
    public void readPaymentAPI(String OrderID) {
        String url = "https://hushed-charming-clipper.glitch.me/payment";
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("OrderID", OrderID));
        
        makeHttpRequest(url, "GET", params);
    }
    
    public void deletePayment(String PaymentID) {
        String url = "https://hushed-charming-clipper.glitch.me/payment";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("PaymentID", PaymentID));
        
        makeHttpRequest(url, "DELETE", params);
    }
    

}
