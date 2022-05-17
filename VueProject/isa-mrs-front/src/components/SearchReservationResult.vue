<template>
    <router-link exact :to="'/'+serviceType+'/'+searchResult.id" tag="div" class="card mb-5 mx-2">
        <img :src="require('@/assets/' + searchResult.pictures[0])" class="card-img-top" alt="Nema slike">
        <div class="card-body">
            <h5 class="card-title">{{searchResult.name}}</h5>
            <div>
                <span class="card-text">Address</span>
                <span class="float-end">{{ searchResult.street }}, {{searchResult.city}}, {{searchResult.country}}</span>
            </div>
            <div>
                <span class="card-text">Description</span>
                <span class="float-end">{{ searchResult.description }}</span>
            </div>
            <div>
                <span class="card-text">Average rating</span>
                <span class="card-text float-end">4.5/5</span>
            </div>
            <div>
                <span class="card-text">Price</span>
                <span class="card-text float-end">{{searchResult.price}}<span>&#8364;</span>, per day</span>
            </div>
            <button @click="reserve" class="btn btn-primary my-3">Make a reservation</button>
      </div>
    </router-link>
</template>

<script>
export default ({
    props: ['searchResult', 'serviceType'],
    methods: {
        reserve(event) {
            event.stopPropagation();
            this.$emit("reserve", this.searchResult);
        },
    },
})
</script>