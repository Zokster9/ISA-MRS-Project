<template>
    <div class="d-flex flex-row">
        <div class="mx-auto">
            <PersonalInfoUser v-if="user" :user="user"></PersonalInfoUser>
        </div>
    </div>
</template>

<script>
    import PersonalInfoUser from '@/components/PersonalInfoUser.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        name: "ProfilePageClient",
        components: {
            PersonalInfoUser,
        },
        data() {
            return {
                user: null,
            }
        },
        mounted(){
            axios.get("http://localhost:8088/users/getLoggedUser",{
                headers:{
                    Authorization: 'Bearer ' + window.localStorage.getItem("accessToken")
                }
            }).then((response) =>{
                this.user = response.data
            })
        }
    }
</script>