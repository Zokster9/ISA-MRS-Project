<template>
    <div class="d-flex flex-row">
        <div>
            <NavbarFishingInstructor></NavbarFishingInstructor>
        </div>
        <div class="row justify-content-center">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> Add new service availability </th>
                    </thead>
                    <tbody>
                        <ServiceAvailabilityRow v-for="service in services" :service="service" :key="service.id"></ServiceAvailabilityRow>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>
<script>
    import ServiceAvailabilityRow from '@/components/ServiceAvailabilityRow.vue'
    import NavbarFishingInstructor from '@/components/NavbarFishingInstructor.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        name: 'ServiceAvailability',
        components: {
            ServiceAvailabilityRow,
            NavbarFishingInstructor
        },
        data(){
            return{
                services: []
            }
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