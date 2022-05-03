<template>
    <div class="d-flex flex-row">
        <div>
            <NavbarUser></NavbarUser>
        </div>
        <div class="row justify-content-center">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> My services </th>
                    </thead>
                    <tbody>
                        <ServiceCrudRow v-for="service in services" :service="service" :key="service.id"></ServiceCrudRow>
                        <tr>
                            <td colspan="2"><input v-model="searchText" type="text" class="form-control" placeholder="Search by name, address, or conduct."></td>
                            <td> <button type="button" class="btn btn-primary float-end" @onclick="search">Search services</button></td>
                        </tr>
                    </tbody>
                </table>
                    <!-- dopuni u zavisnosti od userType na koju stranicu da ide u zavisnosti od korisnika -->
                    <router-link exact to="/add-adventure"> <button type="button" class="btn btn-success float-end"> Add a service </button> </router-link>
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
        },
        mounted(){
            axios.get("http://localhost:8088/users/findMyEntities", {
				headers: {
					Authorization: 'Bearer ' + window.localStorage.getItem("accessToken")
				}
			}).then((response) =>{
                this.services = response.data
            })
        },
    }
</script>
