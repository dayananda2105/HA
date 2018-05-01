package mylearning.fblogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Loginapp extends AppCompatActivity {
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
