<template>
    <div v-if="subscriptions" class="d-flex flex-row">
        <div>
            <NavbarClient></NavbarClient>
        </div>
        <div class="mx-auto" style="margin-top: 120px">
            <SearchResult v-for="subscription in subscriptions" :searchResult="subscription" :key="subscription.id"></SearchResult>
        </div>
    </div>
</template>

<script>
    import NavbarClient from '@/components/NavbarClient.vue'
    import SearchResult from '@/components/SearchResult.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        name: 'ClientSubscriptions',
        components: {
            NavbarClient,
            SearchResult,
        },
        data () {
            return {
                subscriptions: null,
            }
        },
        mounted() {
            if (window.sessionStorage.getItem("role") === "ROLE_client") {
                axios.get("http://localhost:8088/services/allSubscriptions", {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                })
                .then(response => {
                    this.subscriptions = response.data;
                })
                .catch(() => {
                    alert("Something went wrong!");
                })
            }
            else {
                router.push("/");
            }
        },
    }
</script>