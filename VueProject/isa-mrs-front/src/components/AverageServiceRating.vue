<template>
    <div class="d-flex flex-column">
        <template v-for="serviceRating in serviceRatings">
            <div class="d-flex flex-row rounded" :key="serviceRating.id" style="border-radius: 25px; margin: 5px; border: 1px solid #323539">
                <img :src="require('@/assets/' + serviceRating.pictures[0])" style="width:200px; height:200px" class="rounded">
                <div class="d-flex flex-column">
                    <h5 style="margin: 10px">{{serviceRating.serviceName}}</h5>
                    <div class="d-flex flex-row">
                        <label style="margin:5px">Average rating:</label>
                        <h5>{{serviceRating.averageRating}}</h5>
                    </div>
                </div>
            </div>
        </template>
    </div>
</template>

<script>
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        data () {
            return {
                serviceRatings: []
            }
        },
        mounted() {
            axios.get('http://localhost:8088/user-service-ratings', {
                headers: {
                    Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')               
                }
            });
        }
    }

</script>
