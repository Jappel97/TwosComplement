package com.example.aweso.twoscomplement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity
{
    private TextView answerTV;
    private EditText inputET;
    private Switch negativeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.answerTV = (TextView)this.findViewById(R.id.answerTV);
        this.inputET = (EditText)this.findViewById(R.id.inputET);
        this.negativeSwitch = (Switch)this.findViewById(R.id.negativeSwitch);
    }

    private String flipTheBits(String bin)
    {
        String answer = "";
        for(int i = 0; i < bin.length(); i++)
        {
            answer += bin.charAt(i) == '0' ? '1' : '0';
        }
        return answer;
    }

    private String addOne(String bin)
    {
        //will return a new String that is the given String with 1 added to it
        //the initial adding one can be thought of as a carry from the naught position
        boolean carry = true;
        char[] binArr = bin.toCharArray();
        //itterate through the string backwards
        for(int i = bin.length()-1; i >= 0; i--)
        {
            //if there's a carry, then we can add one to the current position. otherwise, nothing is going to change
            if(carry)
            {
                carry = false;
                if(binArr[i] == '0')
                {
                    binArr[i] = '1';
                    carry = false;
                }
                else
                {
                    binArr[i] = '0';
                    carry = true;
                }
            }
            else
            {
                break;
            }
        }
        String str = new String(binArr);
        return str;
    }

    private String encodeAsTwosComplement(String bin)
    {
        String bitsFlipped = this.flipTheBits(bin);
        String oneAdded = this.addOne(bitsFlipped);
        return oneAdded;
    }

    public void onConvertButtonPressed(View v)
    {
        String theBinaryNumber = this.inputET.getText().toString();

        if(this.negativeSwitch.isChecked())
        {
            this.answerTV.setText(this.encodeAsTwosComplement(theBinaryNumber));
        }
        else
        {
            this.answerTV.setText(theBinaryNumber);
        }

    }
}
