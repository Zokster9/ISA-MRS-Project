<template>
    <div v-if="client" class="d-flex flex-row">
        <div>
            <NavbarClient></NavbarClient>
        </div>
        <div class="homepage mx-auto" style="margin-top: 120px">
            <h1>Your current penalty points status:</h1>
            <div class="progress my-3">
                <div class="progress-bar bg-danger" role="progressbar" :style="{ width: penaltyPercentage+'%' }" :aria-valuenow="penaltyPercentage" aria-valuemin="0" aria-valuemax="100">{{client.penaltyPoints}} / 3</div>
            </div>
            <h5 v-if="client.penaltyPoints < 3">If you have 3 or more penalty points you CANNOT make reservations in the current month!</h5>
            <h5 class="penaltyMessage" v-else>You have been penalized! You CANNOT make reservations in the current month!</h5>
        </div>
    </div>
</template>

<script>
    import Vue from 'vue'
    import NavbarClient from '@/components/NavbarClient.vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        name: 'PenaltyPoints',
        components: {
            NavbarClient
        },
        data () {
            return {
                client: null,
            }
        },
        computed: {
            penaltyPercentage() {
                return Math.round((this.client.penaltyPoints / 3) * 100);
            }
        },
        mounted () {
            if (window.sessionStorage.getItem('role') === "ROLE_client") {
                axios.get("http://localhost:8088/clients/getLoggedClient", {
                headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                })
                .then(response => {
                    this.client = response.data;
                })
                .catch(() => {
                    alert("Something went wrong!");
                })
            }
            else {
                router.push("/");
            }
        }
    }
</script>