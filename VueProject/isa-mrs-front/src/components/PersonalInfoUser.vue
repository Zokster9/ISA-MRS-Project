<template>
    <div>
        <div class="card mb-3 border-primary mt-5" style="max-width: 18rem;"> 
            <div class="card-body"> 
                <h5 class="card-title"> Welcome, {{ user.name }}  {{ user.surname }} </h5>
                <h6 class="card-subtitle mb-2 text-muted">Preview of personal info: </h6>
                <p class="card-text"> Email address: {{ user.email }} </p> <p> Address: {{ user.addressDTO.country }}, {{ user.addressDTO.city }}, {{ user.addressDTO.street }}
                 </p><p v-if="user.loyaltyPoints > - 1"> Loyalty status: {{ user.loyaltyStatus }} </p> <p v-if="user.loyaltyPoints > -1">Loyalty points: {{ user.loyaltyPoints }} </p>
            </div>
        </div>
        <div class="card mb-3 border-primary" style="max-width: 18rem;"> 
            <div class="card-body"> 
                <h5 class="card-title"> Change personal info </h5>
                <p class="card-text"> Edit your personal info you have entered when registering. </p>
                <router-link exact to="/change-info" class="btn btn-primary"> Edit personal info </router-link>
            </div>
        </div>
        <div class="card mb-3 border-primary" style="max-width: 18rem;"> 
            <div class="card-body"> 
                <h5 class="card-title"> Change your password </h5>
                <p class="card-text"> Change your current password with a new one. </p>
                <button v-if="!isChangingPassword" class="btn btn-primary" @click="isChangingPassword = true"> Change password </button>
                <input v-if="isChangingPassword" type="password" class="form-control mb-1" v-model="passwordChange.newPassword" placeholder="Enter new password: ">
                <input v-if="isChangingPassword" type="password" class="form-control mb-2" v-model="passwordChange.confirmNewPassword" placeholder="Confirm new password: ">
                <button v-if="isChangingPassword" @ :disabled="$v.passwordChange.$invalid" class="btn btn-success" @click="changePassword"> Confirm new password</button>
                <button v-if="isChangingPassword" class="btn btn-primary" @click="isChangingPassword = false"> Back </button>
            </div>
        </div>
        <div v-if="user.loyaltyPoints > -1" class="card mb-3 border-danger" style="max-width: 18rem;"> 
            <div class="card-body text-danger"> 
                <h5 class="card-title"> Account termination </h5>
                <p class="card-text"> Once you terminate your account, you won't be able to have access to it ever again! </p>
                <button v-if="!isTerminating" class="btn btn-danger" @click="isTerminating = true"> Terminate account </button>
                <textarea v-if="isTerminating" v-model="terminationReason" placeholder="Enter the reason for termination:"></textarea>
                <button v-if="isTerminating" @ :disabled="$v.terminationReason.$invalid" class="btn btn-danger" @click="deleteAccount"> Confirm termination</button>
                <button v-if="isTerminating" class="btn btn-primary" @click="isTerminating = false"> Back </button>
            </div>
        </div>
    </div>
</template>

<script>
    import Vue from 'vue'
    import Vuelidate from 'vuelidate'
    import { required, sameAs } from 'vuelidate/lib/validators'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    export default {
        props: ["user"],
        data () {
            return {
                isTerminating: false,
                terminationReason: "",
                isChangingPassword: false,
                passwordChange: {
                    newPassword: "",
                    confirmNewPassword: ""
                },
            }
        },
        validations: {
            terminationReason: {
                required,
            },
            passwordChange: {
                newPassword: {
                    required,
                },
                confirmNewPassword: {
                    required,
                    sameAsPassword: sameAs('newPassword')
                }
            },
        },
        methods: {
            deleteAccount(){
                axios.post("http://localhost:8088/users/sendTerminationReason", 
				{
					terminationReason: this.terminationReason
				},
				{
					headers: {
						Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
					}
				})
                .then(() => {
                    alert("Successfully sent termination reason.")
                    window.sessionStorage.removeItem('accessToken');
                    window.sessionStorage.removeItem('role');
                    this.$router.push("/")
                })
            },
            changePassword(){
                axios.post("http://localhost:8088/users/changePassword", {
                    newPassword: this.passwordChange.newPassword,
                    confirmPassword: this.passwordChange.confirmNewPassword
                }, {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                })
                .then(()=>{
                    alert("Password successfully changed!")
                    window.location.reload();
                }).catch(() => {
                    alert("Old password is incorrect!")
                })
            },
        },
    }
</script>