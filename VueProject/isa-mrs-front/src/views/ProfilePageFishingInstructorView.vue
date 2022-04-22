<template>
    <div class="d-flex flex-row">
        <div>
            <NavbarFishingInstructor></NavbarFishingInstructor>
        </div>
        <div class="mx-auto">
            <PersonalInfoUser v-if="user" :user="user"></PersonalInfoUser>
        </div>
    </div>
</template>

<script>
    import NavbarFishingInstructor from '@/components/NavbarFishingInstructor.vue'
    import PersonalInfoUser from '@/components/PersonalInfoUser.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        name: 'ProfilePageFishingInstructor',
        components: {
            NavbarFishingInstructor,
            PersonalInfoUser
        },
        data() {
            return {
                user: "",
            }
        },
        //TODO:tu ce biti neki jwt za email
        mounted(){
            axios.get("http://localhost:8088/users/findByEmail/lordje@gmail.com").then((response) =>{
                this.user = response.data
            })
        }
    }
</script>