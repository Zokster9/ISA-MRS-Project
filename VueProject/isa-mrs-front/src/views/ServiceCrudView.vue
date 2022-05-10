<template>
    <div class="d-flex flex-row">
        <div>
            <NavbarUser></NavbarUser>
        </div>
        <div class="row justify-content-center mx-auto">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> My services </th>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="text-center align-items"> <b> Service name </b> </td>
                            <td class="text-center align-items"> <b> EDIT </b> </td>
                            <td class="text-center align-items"> <b> DELETE </b> </td>
                            <td class="text-center align-items"> <b> ADD ACTION </b> </td>
                        </tr>
                        <ServiceCrudRow v-for="service in services" :service="service" :key="service.id"></ServiceCrudRow>
                        <tr>
                            <td colspan="2"><input v-model="searchText" type="text" class="form-control" placeholder="Search by name, address, or conduct."></td>
                            <td class="text-center" colspan="2"> <button type="button float-center" class="btn btn-primary" @onclick="search">Search services</button></td>
                        </tr>
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
                userType: ""
            }
        },
        methods: {
                //searchText se salje na back
                search(){
                    //TODO
                },

                addService() {
                    if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor"){
                        router.push('/add-adventure');
                    } else if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner"){
                        router.push('/add-retreat');
                    } else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner"){
                        router.push('/add-ship');
                    }
                }
        },
        mounted(){
            axios.get("http://localhost:8088/users/findMyEntities", {
				headers: {
					Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
				}
			}).then((response) =>{
                this.services = response.data
                if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor"){
                    this.userType = "fishingInstructor";
                } else if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner"){
                    this.userType = "retreatOwner"
                } else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner"){
                    this.userType = "shipOwner"
                }
            })
        },
    }
</script>
