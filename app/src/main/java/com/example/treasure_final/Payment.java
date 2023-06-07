package com.example.treasure_final;
 import android.app.Activity;
 import android.content.Context;
 import android.widget.Toast;

 import com.razorpay.Checkout;
 import com.razorpay.PaymentResultListener;

 import org.json.JSONException;
 import org.json.JSONObject;

 public class Payment implements PaymentResultListener {

     private Context context;

     public Payment(Context context) {
         this.context = context;
     }

     public void startPayment() {
         Checkout checkout = new Checkout();
         checkout.setImage(R.mipmap.ic_launcher);
         final Activity activity = (Activity) context;

         try {
             JSONObject options = new JSONObject();
             options.put("name", context.getResources().getString(R.string.app_name));
             options.put("description", "Payment for Anything");

                   options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
                                  options.put("theme.color", "#3399cc");


             options.put("currency", "INR");
             options.put("amount", "100");

             JSONObject prefill = new JSONObject();
             prefill.put("email", " ");
             prefill.put("contact", "");

              JSONObject retryObj = new JSONObject();
                  retryObj.put("enabled", true);
                  retryObj.put("max_count", 4);

                  options.put("retry", retryObj);
                  checkout.open(activity, options);
         } catch (Exception e) {
             Toast.makeText(context, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
             e.printStackTrace();
         }
     }

     @Override
     public void onPaymentSuccess(String s) {
         Toast.makeText(context, "Payment Success" + s, Toast.LENGTH_SHORT).show();
     }

     @Override
     public void onPaymentError(int i, String s) {
         Toast.makeText(context, "Payment Error" + s, Toast.LENGTH_SHORT).show();
     }
 }

















