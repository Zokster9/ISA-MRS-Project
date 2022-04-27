<template>
    <div class="d-flex flex-row">
        <div>
            <NavbarUser></NavbarUser>
        </div>
        <div class="mx-auto">
            <PersonalInfoUser v-if="user" :user="user"></PersonalInfoUser>
        </div>
    </div>
</template>

<script>
    import NavbarUser from '@/components/NavbarUser.vue'
    import PersonalInfoUser from '@/components/PersonalInfoUser.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        name: 'ProfilePageShipOwner',
        components: {
            NavbarUser,
            PersonalInfoUser
        },
        data() {
            return {
                user: "",
            }
        },
        mounted(){
            axios.get("http://localhost:8088/users/getLoggedUser", {
				headers: {
					Authorization: 'Bearer ' + window.localStorage.getItem("accessToken")
				}
			}).then((response) =>{
                this.user = response.data
            })
        }
    }
</script>