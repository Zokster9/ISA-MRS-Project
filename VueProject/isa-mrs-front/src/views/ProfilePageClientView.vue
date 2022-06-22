<template>
    <div class="d-flex flex-row">
        <div>
            <NavbarClient></NavbarClient>
        </div>
        <div class="mx-auto" style="margin-top: 50px;">
            <PersonalInfoUser v-if="user" :user="user"></PersonalInfoUser>
        </div>
    </div>
</template>

<script>
    import PersonalInfoUser from '@/components/PersonalInfoUser.vue'
    import NavbarClient from '@/components/NavbarClient.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        name: "ProfilePageClient",
        components: {
            PersonalInfoUser,
            NavbarClient,
        },
        data() {
            return {
                user: null,
            }
        },
        mounted(){
            if (window.sessionStorage.getItem('role') === "ROLE_client") {
                axios.get("http://localhost:8088/users/getLoggedUser",{
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) =>{
                    this.user = response.data
                })
            }
            else {
                router.push("/");
            }
        }
    }
</script>