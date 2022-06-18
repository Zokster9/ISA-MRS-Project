<template>
    <div id="fishing-instructors-screen">
        <NavbarClient v-if="isClient"></NavbarClient>
        <NavbarGuest v-else></NavbarGuest>
        <div class="d-flex">
            <InstructorSidebar @sort="sort" @search="search" style="background-color: #ffffff;"></InstructorSidebar>
            <div style="width:50%;height:100%;margin:auto;margin-top:110px;">
                <div v-if="searchResults" class="vertical-center">
                    <SearchResultInstructor v-for="searchResult in orderedVisibleResults" :searchResult="searchResult" :key="searchResult.id"></SearchResultInstructor>
                </div>
                <div>
                    <PaginationComponent :elements="orderedResults" v-on:page:update="updatePage" :currentPage="currentPage" 
                    :pageSize="pageSize"></PaginationComponent>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import _orderBy from 'lodash/orderBy';
    import NavbarGuest from '@/components/NavbarGuest.vue'
    import NavbarClient from '@/components/NavbarClient.vue'
    import SearchResultInstructor from '@/components/SearchResultInstructor.vue'
    import InstructorSidebar from '@/components/InstructorSidebar.vue'
    import PaginationComponent from '@/components/PaginationComponent.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        name: 'FishingInstructorsView',
        components: {
            NavbarGuest,
            SearchResultInstructor,
            InstructorSidebar,
            NavbarClient,
            PaginationComponent,
        },
        data () {
            return {
                searchResults: null,
                visibleSearchResults: null,
                currentPage: 0,
                pageSize: 5,
                sortBy: "name",
            }
        },
        computed: {
            isClient() {
                return sessionStorage.getItem("role");
            },
            orderedVisibleResults() {
                if (this.sortBy == "name") {
                    return _orderBy(this.visibleSearchResults, this.sortBy)
                }
                return _orderBy(this.visibleSearchResults, this.sortBy, 'desc') 
            },
            orderedResults() {
                if (this.sortBy == "name") {
                    return _orderBy(this.searchResults, this.sortBy)
                }
                return _orderBy(this.searchResults, this.sortBy, 'desc') 
            },
        },
        methods: {
            sort(sortBy) {
                this.sortBy = sortBy;
            },
            search(form) {
                axios.get("http://localhost:8088/users/searchInstructors", {
                    params: {
                        name: form.name,
                        surname: form.surname,
                        address: form.address,
                        city: form.city,
                        country: form.country,
                        phoneNumber: form.phoneNumber,
                    }
                })
                .then(response => {
                    this.searchResults = response.data;
                    this.updateVisibleUsers();
                })
                .catch(() => {
                    alert("Something went wrong!");
                })
            },
            updateVisibleUsers(){
                this.visibleSearchResults = this.searchResults.slice(this.currentPage * this.pageSize, (this.currentPage * this.pageSize) + this.pageSize);
                if (this.visibleSearchResults.length == 0 && this.currentPage > 0){
                    this.updatePage(this.currentPage - 1);
                }
            },
            updatePage(pageNumber){
                this.currentPage = pageNumber;
                this.updateVisibleUsers();
            },
        },
        mounted () {
            axios.get("http://localhost:8088/users/getAllInstructors")
            .then(response => {
                this.searchResults = response.data;
                this.updateVisibleUsers();
            })
            .catch(() => {
                alert("Something went wrong!");
            });
        }
    }
</script>