<template>
    <div id="fishing-instructors-screen">
        <NavbarClient v-if="isClient"></NavbarClient>
        <NavbarGuest v-else></NavbarGuest>
        <div class="d-flex">
            <SearchSidebar style="background-color: #ffffff;"></SearchSidebar>
            <div style="width:50%;height:100%;margin:auto;margin-top:110px;">
                <div v-if="searchResults" class="vertical-center">
                    <SearchResult v-for="searchResult in searchResults" :searchResult="searchResult" :key="searchResult.id"></SearchResult>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import NavbarGuest from '@/components/NavbarGuest.vue'
    import NavbarClient from '@/components/NavbarClient.vue'
    import SearchResult from '@/components/SearchResult.vue'
    import SearchSidebar from '@/components/SearchSidebar.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        name: 'FishingInstructorsView',
        components: {
            NavbarGuest,
            SearchResult,
            SearchSidebar,
            NavbarClient,
        },
        data () {
            return {
                searchResults: null,
            }
        },
        computed: {
            isClient() {
                return sessionStorage.getItem("role");
            }
        },
        mounted () {
            axios.get("http://localhost:8088/adventures/getAll")
            .then(response => {
                this.searchResults = response.data;
            })
            .catch(() => {
                alert("Something went wrong!");
            });
        }
    }
</script>