package mylearning.fblogin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Openpage extends AppCompatActivity {
    EditText text1;
    TextView dp1,dp2;
    private static final int CAMERA_PIC_REQUEST=1;
    Button cal,back;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.openpage);
        firebaseAuth=FirebaseAuth.getInstance();
        text1=(EditText)findViewById(R.id.text1);
        cal=(Button)findViewById(R.id.cal);
        back=(Button)findViewById(R.id.back);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
               try {
                   Toast.makeText(getApplicationContext(), "this is logined page",Toast.LENGTH_SHORT).show();
               }
            catch(RuntimeException e){
                Toast.makeText(getApplicationContext(), "get permission of camera ",Toast.LENGTH_SHORT).show();
            }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mov = new Intent(Openpage.this, Loginapp.class);
                        startActivity(mov);
            }
        });
    }

    public static class Loginapp extends AppCompatActivity {
        Button log,reg;
        EditText un,ps,mail;
        LinearLayout lp;
        FirebaseAuth firebaseAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_loginapp);
            firebaseAuth=FirebaseAuth.getInstance();
            log=(Button)findViewById(R.id.log);
            reg=(Button)findViewById(R.id.reg);
            un=(EditText)findViewById(R.id.un);
            ps=(EditText)findViewById(R.id.ps);
            lp=(LinearLayout)findViewById(R.id.lp);
            log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(un.getText().toString().equals("daya")&&ps.getText().toString().equals("2105"))
                    {
                        Intent go=new Intent(Loginapp.this,Openpage.class);
                        startActivity(go);
                        Toast.makeText(Loginapp.this, "u r valid", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        firebaseAuth.signInWithEmailAndPassword(un.getText().toString(), ps.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Loginapp.this, "login succesful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Loginapp.this, Openpage.class));
                                } else {
                                    Toast.makeText(Loginapp.this, "login failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            });
            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent gos=new Intent(Loginapp.this,Register.class);
                    startActivity(gos);
                }
            });

        }
    }
}

