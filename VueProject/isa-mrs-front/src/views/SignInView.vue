<template>
    <div class="signIn">
        <NavbarGuest></NavbarGuest>
        <div class="app">
            <div class="vertical-center">
                <div class="inner-block">
                    <div class="signIn">
                        <form @submit.prevent>
                            <h3>Sign In</h3>
                            
                            <div class="form-group required">
                                <label class="control-label">E-mail </label>
                                <input v-model="form.email" type="email" class="form-control form-control-lg" placeholder="Please enter your email...">
                            </div>
                            
                            <div class="form-group required">
                                <label class="control-label">Password </label>
                                <input v-model="form.password" type="password" class="form-control form-control-lg" placeholder="Please enter your password...">
                            </div>
                            
                            <div class="form-group">
                                <button @click="signIn" :disabled="$v.form.$invalid" type="submit" class="btn btn-dark btn-lg btn-block">Sign In</button>
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
    import { required, minLength, email } from 'vuelidate/lib/validators'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import router from '@/router'

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    export default ({
        name: 'SignInView',
        components: {
            NavbarGuest,
        },
        data () {
            return {
                form: {
                    email: "",
                    password: ""
                }
            }
        },
        methods: {
            signIn() {
                axios.post("http://localhost:8088/auth/login", {
                    email: this.form.email,
                    password: this.form.password
                }).then(response => {
					localStorage.setItem("accessToken", response.data.accessToken);
					localStorage.setItem("role", response.data.roleName);
					if (response.data.roleName === "ROLE_retreatOwner") {
						// TODO: prebaciti na stranicu vlasnika vikendice
					}else if (response.data.roleName === "ROLE_shipOwner") {
						// TODO: prebaciti na stranicu vlasnika broda
                        router.push("/service-crud");
					}else if (response.data.roleName === "ROLE_fishingInstructor") {
						// TODO: prebaciti na stranicu instruktora pecanja
						router.push("/profile-page-fishing-instructor");
					}else if (response.data.roleName === "ROLE_client") {
						// TODO: prebaciti na stranicu klijenta
					}else if (response.data.roleName === "ROLE_admin") {
						// TODO: prebaciti na stranicu admina
					}else {
						alert("Some kind of error, dont know what.");
					}
                }).catch(() => {
                    alert("Incorrect login details")
                    console.log("Ne ide brt")
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
                }
            }
        }
    })
</script>