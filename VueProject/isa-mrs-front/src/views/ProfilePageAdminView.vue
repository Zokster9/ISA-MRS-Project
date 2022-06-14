<template>
    <div class="d-flex flex-row">
        <div v-if="!isFirstTimeAdmin">
            <NavbarAdmin></NavbarAdmin>
        </div>
        <div class="mx-auto my-5" v-if="!isFirstTimeAdmin">
            <PersonalInfoUser v-if="data" :user="data"></PersonalInfoUser>
        </div>
        <div class="mx-auto" v-if="isFirstTimeAdmin"> 
            <div class="card mb-3 border-primary mt-5" style="max-width: 18rem;">
                <div class="card-body"> 
                    <h5 class="card-title"> Welcome, {{ data.name }} {{ data.surname }}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Please enter your new password: </h6>
                    <input type="password" class="form-control mb-1" v-model="password.newPassword" placeholder="Enter new password: ">
                    <input type="password" class="form-control mb-2" v-model="password.confirmNewPassword" placeholder="Confirm new password: ">
                    <button class="btn btn-primary float-end" @ :disabled="$v.password.$invalid" @click="changeAdminPassword"> Confirm </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import NavbarAdmin from '@/components/NavbarAdmin.vue'
    import PersonalInfoUser from '@/components/PersonalInfoUser.vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import Vue from 'vue'
    import Vuelidate from 'vuelidate'
    import { required, sameAs } from 'vuelidate/lib/validators'
import router from '@/router'

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    export default {
        name: 'ProfilePageAdmin',
        components: {
            NavbarAdmin,
            PersonalInfoUser
        },
        data(){
            return {
                data: "",
                password:{
                    newPassword: "",
                    confirmNewPassword: ""
                },
                isFirstTimeAdmin: ""
            }
        },
        methods:{
            changeAdminPassword(){
                axios.put("http://localhost:8088/users/activateAdmin",
                {
                    newPassword: this.password.newPassword,
                    confirmPassword: this.password.confirmNewPassword
                },
                {
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then(()=>{
                    window.location.reload();
                })
            }
        },
        validations:{
            password:{
                newPassword:{
                    required,
                    sameAsPassword: sameAs('confirmNewPassword')
                },
                confirmNewPassword:{
                    required,
                    sameAsPassword: sameAs('newPassword')
                }
            }
        },
        mounted () {
            if (window.sessionStorage.getItem('role') === "ROLE_admin" || window.sessionStorage.getItem("role") === "ROLE_mainAdmin") {
                axios.get("http://localhost:8088/users/getLoggedAdmin",
                {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response)=>{
                    this.data = response.data;
                    if (this.data.passwordChanged === false && window.sessionStorage.getItem("role") === "ROLE_admin"){
                        this.isFirstTimeAdmin = true;
                    }
                    else{
                        this.isFirstTimeAdmin = false;
                    }
                })
            }
            else {
                router.push("/");
            }
        }
    }
</script>