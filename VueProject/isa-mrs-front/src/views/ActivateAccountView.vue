<template>
    <div>
        <NavbarGuest></NavbarGuest>
        <div style="margin-top: 80px">
            <h1 style="text-align: center;"> Account successfully activated </h1>
            <h3 style="text-align: center;"> Thank you for using our services! </h3>
        </div>
    </div>
</template>

<script>
    import NavbarGuest from '@/components/NavbarGuest.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        name: 'ActivateAccount',
        components: {
            NavbarGuest,
        },
        mounted() {
            axios.post("http://localhost:8088/users/activate-client/" + this.$route.params.id)
            .then(() => {
                console.log("Uspesna aktivacija");
            })
            .catch(() => {
                console.log("Greska.")
            })
        }
    }
</script>