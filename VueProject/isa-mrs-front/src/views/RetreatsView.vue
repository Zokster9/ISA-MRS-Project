<template>
    <div id="retreats-screen">
        <NavbarGuest></NavbarGuest>
        <div class="d-flex">
            <SearchSidebar style="background-color: #ffffff;"></SearchSidebar>
            <div style="width:50%;height:100%;margin:auto;margin-top:100px;">
                <div v-if="searchResults" class="vertical-center">
                    <SearchResult v-for="searchResult in searchResults" :searchResult="searchResult" :key="searchResult.id"></SearchResult>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import NavbarGuest from '@/components/NavbarGuest.vue'
    import SearchResult from '@/components/SearchResult.vue'
    import SearchSidebar from '@/components/SearchSidebar.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        name: 'RetreatsView',
        components: {
            NavbarGuest,
            SearchResult,
            SearchSidebar
        },
        data () {
            return {
                searchResults: null,
            }
        },
        mounted () {
            axios.get("http://localhost:8088/retreats/")
            .then(response => {
                this.searchResults = response.data;
            });
        }
    }
</script>