import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 
  myform: FormGroup;
  username:FormControl;
  password:FormControl;
  ngOnInit() {

    this.createFormControls();
    this.createForm();
  }
  
  createFormControls() {

     this.username=new FormControl('', Validators.required);
     this.password=new FormControl('',Validators.required);
  }
  createForm() {

     this.myform = new FormGroup({
        
        username: this.username,
        password:this.password
        
    });  
  }

  onLogIn() {
  alert("Logged in !");
  }

}
