package mylearning.fblogin;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {
    EditText d1,d3, d2;
    Button reg,k;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        firebaseAuth=FirebaseAuth.getInstance();
        d1 = (EditText) findViewById(R.id.d1);
        d2 = (EditText) findViewById(R.id.d2);
        k = (Button) findViewById(R.id.k);
        reg=(Button)findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    String dp1=d1.getText().toString().trim();
                    String dp2=d2.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(dp1,dp2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                            Toast.makeText(Register.this, "registration is successful",Toast.LENGTH_SHORT).show();
                                Intent mov = new Intent(Register.this, Openpage.Loginapp.class);
                                startActivity(mov);
                        }else {
                                Toast.makeText(Register.this, "registration is failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mov = new Intent(Register.this, Openpage.Loginapp.class);
                startActivity(mov);
            }
        });
    }
    private Boolean validate(){
        Boolean result=false;
        String name=d1.getText().toString();
        String password=d2.getText().toString();
        if (name.isEmpty()||password.isEmpty())
        {
            Toast.makeText(this, "please enter all details",Toast.LENGTH_SHORT).show();
        }
        else {
            result=true;
        }
        return result;
    }
   }
