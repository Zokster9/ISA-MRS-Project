<template>
    <div>
    <NavbarUser></NavbarUser>
    <div class="signUp">    
        <div class="app">
            <div class="vertical-center" style="margin-top: 85px">
                <div class="inner-block">
                    <div class="signUp">
                        <form @submit.prevent>
                            <h3>Change your info:</h3>
                            
                            <div class="form-group required">
                                <label>Email </label>
                                <input v-model="form.email" placeholder="Please enter your email..." readonly type="email" class="form-control form-control-lg">
                            </div>             
                            <div class="form-group required">
                                <table>
                                    <tr>
                                        <td>
                                            <label for="name">Name </label>
                                            <input v-model="form.name" id="name" type="text" placeholder="Name..." class="form-control form-control-lg"/>
                                        </td>
                                        <td>
                                            <label for="surname">Surname </label>
                                            <input v-model="form.surname" id="surname" type="text" placeholder="Surname..." class="form-control form-control-lg"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            
                            <div class="form-group required">
                                <label>Address </label>
                                <input v-model="form.address" type="text" placeholder="Please enter your address..."  class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group required">
                                <table>
                                    <tr>
                                        <td>
                                            <label for="city">City </label>
                                            <input v-model="form.city" id="city" type="text" placeholder="City..." class="form-control form-control-lg"/>
                                        </td>
                                        <td>
                                            <label for="country">Country </label>
                                            <input v-model="form.country" id="country" type="text" placeholder="Country..." class="form-control form-control-lg"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            
                            <div class="form-group required">
                                <label>Phone number </label>
                                <input v-model="form.phoneNumber" type="text" placeholder="Please enter your phone number..." class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group">
                                <button @click="changeInfo"  :disabled="$v.form.$invalid" type="submit" class="btn btn-dark btn-lg btn-block">Change info</button>
                            </div>
                        </form>
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
    import { required, minLength, maxLength, email } from 'vuelidate/lib/validators'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import router from '@/router'
    import NavbarUser from '@/components/NavbarUser.vue'

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    const isCapitalFirstLetter = (value) => RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(value);
    const isPhoneNumberCorrect = (value) => RegExp(/^[+]?(\d{1,2})?[\s.-]?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/).test(value);

    export default {
        components: {
            NavbarUser
        },
        data () {
            return {
                form:{
                    email: "",
                    name: "",
                    surname: "",
                    address: "",
                    city: "",
                    country: "",
                    phoneNumber: ""
                },
				user: null
            }
        },
        methods: {
            changeInfo() {
                axios.post("http://localhost:8088/users/changeInfo", {
                    email: this.form.email,
                    name: this.form.name,
                    surname: this.form.surname,
                    addressDTO: {
                        country: this.form.country,
                        city: this.form.city,
                        street: this.form.address,
                    },
                    phoneNumber: this.form.phoneNumber,
                }, {
					headers: {
						Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
					}
				}).then(() => {
                    alert("Successfully changed personal information!");
                    if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner") {
						this.$router.push("/profile-page-retreat-owner")
					}else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner") {
						this.$router.push("/profile-page-ship-owner")
					}else if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
						this.$router.push("/profile-page-fishing-instructor")
					}else if (window.sessionStorage.getItem("role") === "ROLE_client") {
						this.$router.push("/profile-page-client")
					}else if (window.sessionStorage.getItem("role") === "ROLE_admin" || window.sessionStorage.getItem("role") === "ROLE_mainAdmin") {
						this.$router.push("/profile-page-admin")
					}else {
						alert("Some kind of error, dont know what.");
					}
                }).catch(() => {
                    alert("Something went wrong.")
                });
            }
        },
        validations: {
            form: {
                email : {
                    required,
                    minLength: minLength(3),
                    maxLength: maxLength(50),
                    email
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
        },
		mounted() {
            if (window.sessionStorage.getItem("role")) {
                axios.get("http://localhost:8088/users/getLoggedUser", {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) =>{
                    this.user = response.data;
                    this.form.name = response.data.name;
                    this.form.surname = response.data.surname;
                    this.form.email = response.data.email;
                    this.form.address = response.data.addressDTO.street;
                    this.form.country = response.data.addressDTO.country;
                    this.form.city = response.data.addressDTO.city;
                    this.form.phoneNumber = response.data.phoneNumber;
                })
            }
            else {
                router.push("/");
            }
		}
    }
</script>