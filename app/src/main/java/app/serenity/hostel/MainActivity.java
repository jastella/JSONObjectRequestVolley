package app.serenity.hostel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText,editText2;
    String username=null,password=null;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.result);
        button = (Button)findViewById(R.id.login);
        editText = (EditText)findViewById(R.id.username);
        editText2 = (EditText)findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             username = editText.getText().toString();
             password = editText2.getText().toString();
             if(username.isEmpty() || password.isEmpty())
             {
                 Toast.makeText(getApplicationContext(),"Ingrese los datos.",Toast.LENGTH_LONG).show();
             }
             else
             {
               try
                {
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url = "http://www.fabricaweb.es/ws/userGetLogin.php";
                    /*StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            textView.setText(""+response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"SERVER CONNECTION ERROR"+error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });

                    queue.add(stringRequest);
                    */

                    HashMap<String, String> params = new HashMap<>();
                    params.put("phone", username);
                    params.put("password", password);

                    //Toast.makeText(getApplicationContext(),params.toString(), Toast.LENGTH_SHORT).show();

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject responseJsonObject) {
                                    textView.setText((responseJsonObject).toString());
                                }
                            },
                            new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.d("Volley Error", volleyError.toString());
                                Toast.makeText(getApplicationContext(), "Connectivity Error",
                                        Toast.LENGTH_SHORT).show();
                            }
                    }){
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }
                    };

                    queue.add(request);

                    queue.start();

                }
                catch (Exception e)
                {
textView.setText(e.toString());
                }
             }
            }
        });

    }
}
