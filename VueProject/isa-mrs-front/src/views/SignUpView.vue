<template>
    <div class="signUp">
        <NavbarGuest></NavbarGuest>
        <div class="app">
            <div class="vertical-center" style="margin-top: 85px">
                <div class="inner-block">
                    <div class="signUp">
                        <form @submit.prevent>
                            <h3>Sign Up</h3>
                            <div class="form-check form-check-inline">
                              <input :checked="isClient" v-model="form.registrationType" class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="client">
                              <label class="form-check-label" for="inlineRadio1">Client</label>
                            </div>
                            
                            <div class="form-check form-check-inline float-end">
                              <input v-model="form.registrationType" class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="privilegedUser">
                              <label class="form-check-label" for="inlineRadio2">Privileged User</label>
                            </div>
                            
                            <div class="form-group required">
                                <label class="control-label">Email </label>
                                <input v-model="form.email" placeholder="Please enter your email..." type="email" class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group required">
                                <label class="control-label">Password </label>
                                <input v-model="form.password" placeholder="Please enter your password..." type="password" class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group required">
                                <label class="control-label">Confirm password </label>
                                <input v-model="form.confirmPassword" placeholder="Please confirm your password..." type="password" class="form-control form-control-lg">
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
                                <input v-model="form.address" type="text" placeholder="Please enter your address..." class="form-control form-control-lg">
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
                                <input v-model="form.phoneNumber" type="text" placeholder="Please enter your phone number..." class="form-control form-control-lg">
                            </div>
                            
                            <div v-if="isPrivilegedUser" class="form-group required">
                                <label class="control-label">Privileged user type </label>
                                <select  v-model="form.privilegedUserType" class="form-select" aria-label="Privileged user types">
                                  <option selected value="retreatOwner">Retreat owner</option>
                                  <option value="shipOwner">Ship owner</option>
                                  <option value="fishingInstructor">Fishing instructor</option>
                                </select>
                            </div>
                            
                            <div v-if="isPrivilegedUser" class="form-group required">
                                <label class="control-label">Registration explanation </label>
                                <textarea v-model="form.registrationExplanation" placeholder="Please enter why you should be allowed to register..." class="form-control form-control-lg"></textarea>
                            </div>
                            
                            <div class="form-group">
                                <button @click="register" :disabled="$v.form.$invalid || isExplanationRequired" type="submit" class="btn btn-dark btn-lg btn-block">Sign Up</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import NavbarGuest from '@/components/NavbarGuest.vue'
    import Vue from 'vue'
    import Vuelidate from 'vuelidate'
    import { required, minLength, maxLength, email, sameAs } from 'vuelidate/lib/validators'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import router from '@/router'

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    const isCapitalFirstLetter = (value) => RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(value);
    const isPhoneNumberCorrect = (value) => RegExp(/^[+]?(\d{1,2})?[\s.-]?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/).test(value);

    export default({
        name: 'SignUpView',
        components: {
            NavbarGuest
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
                    privilegedUserType: "retreatOwner",
                    registrationExplanation: "",
                    registrationType: "client",
                }
            }
        },
        computed: {
            isClient() {
                return this.form.registrationType === "client";
            },
            isPrivilegedUser() {
                return this.form.registrationType === "privilegedUser";
            },
            isExplanationRequired() {
                return this.isPrivilegedUser && this.form.registrationExplanation === "";
            }
        },
        methods: {
            register() {
                if (this.isClient) this.form.privilegedUserType = "client"
                axios.post("http://localhost:8088/users/register", {
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
                    registrationType: this.form.registrationType,
                    privilegedUserType: this.form.privilegedUserType,
                    registrationExplanation: this.form.registrationExplanation
                }).then(() => {
                    alert("Registration form was successful. Check your email for further information.");
                    this.$router.push("/main-screen")
                }).catch(() => {
                    alert("Something went wrong.")
                });
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
                    minLength : minLength(13),
                    isPhoneNumberCorrect
                },
            }
        },
        mounted() {
            if (window.sessionStorage.getItem("role")) {
                if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner") {
                    router.push('/profile-page-retreat-owner');
				}else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner") {
					router.push('/profile-page-ship-owner');
				} else if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
					router.push("/profile-page-fishing-instructor");
				} else if (window.sessionStorage.getItem("role") === "ROLE_client") {
					router.push("/home-page-client");
				} else if (window.sessionStorage.getItem("role") === "ROLE_admin" || window.sessionStorage.getItem("role") === "ROLE_mainAdmin") {
					router.push("/profile-page-admin");
				}
            }
        }
    })
</script>
