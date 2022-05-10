<template>
    <router-link exact to="/" tag="div" class="card mb-5 mx-2">
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
      </div>
    </router-link>
</template>

<script>
export default ({
    props: ['searchResult']
})
</script>