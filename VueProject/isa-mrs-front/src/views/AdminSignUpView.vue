<template>
    <div>
        <NavbarAdmin></NavbarAdmin>
        <div class="justify-content-center mx-auto">
            <div class="signUp">
                <div class="app">
                    <div class="vertical-center" style="margin-top: 85px">
                        <div class="inner-block">
                            <div class="signUp">
                                <form @submit.prevent>
                                    <h3>Add new administrator</h3>
                                    
                                    <div class="form-group required">
                                        <label class="control-label">Email </label>
                                        <input v-model="form.email" placeholder="Please enter email..." type="email" class="form-control form-control-lg">
                                    </div>
                                    
                                    <div class="form-group required">
                                        <label class="control-label">Password </label>
                                        <input v-model="form.password" placeholder="Please enter password..." type="password" class="form-control form-control-lg">
                                    </div>
                                    
                                    <div class="form-group required">
                                        <label class="control-label">Confirm password </label>
                                        <input v-model="form.confirmPassword" placeholder="Please confirm password..." type="password" class="form-control form-control-lg">
                                    </div>
                                    
                                    <div class="form-group required">
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="name">Name </label>
                                                    <input v-model="form.name" id="name" type="text" placeholder="Name..." pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                                </td>
                                                <td>
                                                    <label class="control-label" for="surname">Surname </label>
                                                    <input v-model="form.surname" id="surname" type="text" placeholder="Surname..." pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    
                                    <div class="form-group required">
                                        <label class="control-label">Address </label>
                                        <input v-model="form.address" type="text" placeholder="Please enter address..." class="form-control form-control-lg">
                                    </div>
                                    
                                    <div class="form-group required">
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="city">City </label>
                                                    <input v-model="form.city" id="city" type="text" placeholder="City..." pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                                </td>
                                                <td>
                                                    <label class="control-label" for="country">Country </label>
                                                    <input v-model="form.country" id="country" type="text" placeholder="Country..." pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    
                                    <div class="form-group required">
                                        <label class="control-label">Phone number </label>
                                        <input v-model="form.phoneNumber" type="text" placeholder="Please enter phone number..." class="form-control form-control-lg">
                                    </div>
                                    
                                    <div class="form-group">
                                        <button @click="register"  :disabled="$v.form.$invalid" type="submit" class="btn btn-dark btn-lg btn-block">Sign Up</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>            
        </div>
    </div>
</template>

<script>
    import Vue from 'vue'
    import Vuelidate from 'vuelidate'
    import { required, minLength, maxLength, email, sameAs } from 'vuelidate/lib/validators'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import NavbarAdmin from '@/components/NavbarAdmin.vue'
import router from '@/router'

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    const isCapitalFirstLetter = (value) => RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(value);
    const isPhoneNumberCorrect = (value) => RegExp(/^[+]?(\d{1,2})?[\s.-]?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/).test(value);

    export default({
        name: 'AdminSignUpView',
        components: {
            NavbarAdmin
        },
        data () {
            return {
                form: {
                    email: "",
                    password: "",
                    confirmPassword: "",
                    name: "",
                    surname: "",
                    address: "",
                    city: "",
                    country: "",
                    phoneNumber: "",
                }
            }
        },
        computed: {
    
        },
        methods: {
            register() {
                axios.post("http://localhost:8088/users/registerAdmin", {
                    email: this.form.email,
                    password: this.form.password,
                    confirmPassword: this.form.confirmPassword,
                    name: this.form.name,
                    surname: this.form.surname,
                    addressDTO: {
                        country: this.form.country,
                        city: this.form.city,
                        street: this.form.address
                    },
                    phoneNumber: this.form.phoneNumber,
                },
                {
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }
                ).then(() => {
                    alert("Registration form was successful.");
                    this.$router.push("/profile-page-admin")
                }).catch(() => {
                    alert("Something went wrong.")
                });
            }
        },
        mounted() {
            if (window.sessionStorage.getItem("role") != "ROLE_mainAdmin") {
                router.push("/");
            }
        },
        validations: {
            form: {
                email : {
                    required,
                    email
                },
                password: {
                    required,
                    minLength: minLength(1)
                },
                confirmPassword : {
                    required,
                    sameAsPassword : sameAs('password')
                },
                name : {
                    required,
                    minLength: minLength(1),
                    maxLength: maxLength(30),
                    isCapitalFirstLetter
                },
                surname : {
                    required,
                    minLength : minLength(1),
                    maxLength: maxLength(30),
                    isCapitalFirstLetter
                },
                address: {
                    required,
                    minLength : minLength(1),
                },
                city: {
                    required,
                    minLength : minLength(1),
                    isCapitalFirstLetter
                },
                country: {
                    required,
                    minLength : minLength(1),
                    isCapitalFirstLetter
                },
                phoneNumber: {
                    required,
                    minLength : minLength(12),
                    isPhoneNumberCorrect
                },
            }
        }
    })
</script>
