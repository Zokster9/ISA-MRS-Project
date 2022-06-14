<template>
    <div>
        <NavbarUser></NavbarUser>
        <div class="row justify-content-center mx-auto my-4">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> My services </th>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="2"><input v-model="searchText" type="search" class="form-control rounded" aria-label="Search" aria-describedby="search-addon" placeholder="Search by name, address, or conduct."></td>
                            <td class="text-center" colspan="1"> <button type="button float-center" class="btn btn-outline-primary" @click="search">Search services</button></td>
                            <td class="text-center" colspan="1"> <button type="button float-center" class="btn btn-outline-primary" @click="showAll">Show all services</button></td>
                        </tr>
                        <tr>
                            <td colspan="4"></td>
                        </tr>
                        <tr>
                            <td class="text-center align-items"> <b> Service name </b> </td>
                            <td class="text-center align-items"> <b> EDIT </b> </td>
                            <td class="text-center align-items"> <b> DELETE </b> </td>
                            <td class="text-center align-items"> <b> ADD ACTION </b> </td>
                        </tr>
                        <ServiceCrudRow v-for="service in services" :service="service" :key="service.id"></ServiceCrudRow>
                    </tbody>
                </table>
                    <div>
                        <button type="button" @click="addService" class="btn btn-success float-end"> Add a service </button>
                    </div>                                       
            </div>
        </div>
    </div>    
</template>
<script>

    import NavbarUser from '@/components/NavbarUser.vue'
    import ServiceCrudRow from '@/components/ServiceCrudRow.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        name: 'ServiceAvailability',
        components: {
            NavbarUser,
            ServiceCrudRow
        },
        data(){
            return{
                services: [],
                searchText: "",
                userType: "",
                servicesCopy: [],
                activeIndexes: []
            }
        },
        methods: {
                showAll(){
                    while (this.services.length != 0){
                        this.services.pop();
                    }
                    for (let i = 0; i < this.servicesCopy.length; i++){
                        this.services.push(this.servicesCopy[i]);
                    }
                },
                search(){
                    while (this.services.length != 0){
                        this.services.pop();
                    }
                    while (this.activeIndexes.length != 0){
                        this.activeIndexes.pop();
                    }
                    if (this.searchText.length == 0){
                        for (let i = 0; i < this.servicesCopy.length; i++){
                            this.services.push(this.servicesCopy[i]);
                        }
                        return;
                    }
                    for (let i = 0; i < this.servicesCopy.length; i++){
                        if (this.searchByName(this.servicesCopy[i].name) || this.searchByAddress(this.servicesCopy[i].country, this.servicesCopy[i].city, this.servicesCopy[i].street)
                            || this.searchByRulesOfConduct(this.servicesCopy[i].rulesOfConduct)){
                            this.activeIndexes.push(i);
                        }
                    }
                    for (let i = 0; i < this.servicesCopy.length; i++){
                        for (let j = 0; j < this.activeIndexes.length; j++){
                            if (i == this.activeIndexes[j]){
                                this.services.push(this.servicesCopy[i]);
                                break;
                            }
                        }
                    }
                },
                searchByName(name){
                    if (name.includes(this.searchText)){
                        return true;
                    }
                    return false;
                },
                searchByAddress(country, city, street){
                    if (country.toLowerCase().includes(this.searchText.toLowerCase())) return true;
                    if (city.toLowerCase().includes(this.searchText.toLowerCase())) return true;
                    if (street.toLowerCase().includes(this.searchText.toLowerCase())) return true;
                    return false;
                },
                searchByRulesOfConduct(rulesOfConduct){
                    for (let rule of rulesOfConduct) {
                        if (rule.toLowerCase() === this.searchText.toLowerCase()) return true;
                    }
                    return false;
                },
                addService() {
                    if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor"){
                        router.push('/add-adventure');
                    } else if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner"){
                        router.push('/add-retreat');
                    } else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner"){
                        router.push('/add-ship');
                    }
                },
        },
        mounted(){
            if (window.sessionStorage.getItem('role') === "ROLE_retreatOwner" || window.sessionStorage.getItem("role") === "ROLE_shipOwner" || window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
                axios.get("http://localhost:8088/users/findMyEntities", {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) =>{
                    this.services = response.data;
                    this.servicesCopy = JSON.parse(JSON.stringify(response.data));
                    if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor"){
                        this.userType = "fishingInstructor";
                    } else if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner"){
                        this.userType = "retreatOwner"
                    } else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner"){
                        this.userType = "shipOwner"
                    }
                })
            }
            else {
                router.push("/");
            }
        },
    }
</script>
