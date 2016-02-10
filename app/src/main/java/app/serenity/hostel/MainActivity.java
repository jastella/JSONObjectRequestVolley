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
                    String url = "http://www.fabricaweb.es/ws/userGetLogin.php";

                    HashMap<String, String> params = new HashMap<>();
                    params.put("phone", username);
                    params.put("password", password);

                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                    CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST,
                            url, params, this.createRequestSuccessListener(), this.createRequestErrorListener());

                    requestQueue.add(jsObjRequest);
                }
                catch (Exception e)
                {
            textView.setText(e.toString());
                }
             }
            }

            private Response.ErrorListener createRequestErrorListener() {

                return new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(getApplicationContext(), "Connectivity Error",
                                Toast.LENGTH_SHORT).show();
                    }
                };
            }

            private Response.Listener<JSONObject> createRequestSuccessListener() {
                    return new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(), response.toString(),
                                    Toast.LENGTH_SHORT).show();

                        }
                    };
            }
        });

    }
}
