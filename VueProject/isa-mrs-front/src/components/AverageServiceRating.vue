<template>
    <div class="d-flex flex-column">
        <template v-for="serviceRating in serviceRatings">
            <div class="d-flex flex-row rounded" :key="serviceRating.id" style="border-radius: 25px; margin: 5px; border: 1px solid #323539">
                <img v-if="serviceRating.servicePictures.length != 0" :src="require('@/assets/' + serviceRating.servicePictures[0])" style="width:50%; height:200px; margin:10px" class="rounded">
                <img v-else src="@/assets/no-image.png" alt="Nema slike" style="width:50%; height:200px; margin:10px" class="rounded">
                <div class="d-flex flex-column align-items-center" style="width: 400px">
                    <h5 style="margin: 10px">{{serviceRating.serviceName}}</h5>
					<label style="margin:5px">Average rating: <StarRating :show-rating="false" :increment="0.01" :star-size="24" :inline="true" 
                :rating="serviceRating.averageRating" :read-only="true"></StarRating> {{serviceRating.averageRating}}/5</label>
                </div>
            </div>
        </template>
    </div>
</template>

<script>
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import StarRating from 'vue-star-rating'

    Vue.use(VueAxios, axios)

    export default {
        data () {
            return {
                serviceRatings: []
            }
        },
        components: {
            StarRating,
        },
        mounted() {
            axios.get('http://localhost:8088/revisions/user-service-ratings', {
                headers: {
                    Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')               
                }
            }).then(response => {
				this.serviceRatings = response.data;
			}).catch(() => {
				alert("Something went wrong!");
			});
        }
    }

</script>
