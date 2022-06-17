<template>
    <router-link exact :to="'/adventures/' + searchResult.id" tag="div" class="card mb-5 mx-2">
        <div class="card-body">
            <h5 class="card-title">{{searchResult.name}}  {{searchResult.surname}}</h5>
            <div>
                <span class="card-text">Address</span>
                <span class="float-end">{{ searchResult.addressDTO.street }}, 
                    {{searchResult.addressDTO.city}}, {{searchResult.addressDTO.country}}</span>
            </div>
            <div>
                <span class="card-text">Phone number</span>
                <span class="float-end">{{ searchResult.phoneNumber }}</span>
            </div>
            <div v-if="searchResult.averageRating > 0">
                <span class="card-text">Average rating</span>
                <span class="card-text float-end"><StarRating :show-rating="false" :increment="0.01" :star-size="24" :inline="true" 
                :rating="searchResult.averageRating" :read-only="true"></StarRating> {{searchResult.averageRating}}/5</span>
            </div>
            <div v-else>
                <span class="card-text">Average rating</span>
                <span class="card-text float-end">No rating yet.</span>
            </div>
      </div>
    </router-link>
</template>

<script>
import StarRating from 'vue-star-rating'

export default ({
    props: ['searchResult'],
    components: {
        StarRating,
    },
})
</script>