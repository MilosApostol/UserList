package com.example.list.screens

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.list.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
/*
@Composable
fun RegisterScreen(
//    userSignupViewModel: UserSignupViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val auth = Firebase.auth
    var emailAddress by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    var password by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RegisterOutlinedText(
                emailAddress, { emailAddress = it }, KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ), {
                    Text(
                        text = "Email Address"
                    )
                }
            )
            Spacer(modifier = Modifier.height(height = 7.dp))
            RegisterOutlinedText(
                value = password,
                onValueChanged = { password = it },
                applyVisualTransformation = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                label = {
                    Text(
                        text = "Password"
                    )
                },
            )
            Spacer(modifier = Modifier.height(height = 21.dp))
            Button(onClick = {
                auth.createUserWithEmailAndPassword(emailAddress.trim(), password.trim())
                    .addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            Toast.makeText(context, "made", Toast.LENGTH_LONG).show()
                        } else{
                            Toast.makeText(context, "failed", Toast.LENGTH_LONG).show()

                        }

                    }
            }) {
                Text(text = "Register")
            }
        }
    }

}      //        userSignupViewModel.registerUser(email = emailAddress, password = password)
// homeViewModel.getUserDetails()
//       navController.navigate(Routes.Home.name)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterOutlinedText(
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    label: @Composable (() -> Unit)?,
    applyVisualTransformation: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        keyboardOptions = keyboardOptions,
        label = label,
        visualTransformation = if (applyVisualTransformation) PasswordVisualTransformation()
        else VisualTransformation.None
    )
}

// To mask the password entry using the * character, use the following function:
private class PasswordVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString("*".repeat(text.text.length)),

            /**
             * [OffsetMapping.Identity] is a predefined [OffsetMapping] that can be used for the
             * transformation that does not change the character count.
             */
            OffsetMapping.Identity
        )
    }
}

@Preview
@Composable
fun registerShow(){
    RegisterScreen(navController = rememberNavController())
}

 */