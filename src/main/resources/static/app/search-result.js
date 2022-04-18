Vue.component("search-result", {
    template: `
    <router-link exact to="/" tag="div" class="card mb-5 mx-2">
<!--    <img :src="'images/'+searchResult.picture" class="card-img-top" alt="...">-->
    <img src="images/retreat.png" class="card-img-top" alt="...">
      <div class="card-body">
<!--        <h5 class="card-title">{{searchResult.name}}</h5>-->
        <h5 class="card-title">Name</h5>
        <div>
            <span class="card-text">Address</span>
<!--            <span class="float-end">{{ searchResult.address }}, {{searchResult.city}}, {{searchResult.country}}</span>-->
            <span class="card-text float-end">Address, City, Country</span>
        </div>
        <div>
            <span class="card-text">Description</span>
<!--            <span class="float-end">{{ searchResult.description }}</span>-->
            <span class="card-text float-end">Some description</span>
        </div>
        <div>
            <span class="card-text">Average rating</span>
            <span class="card-text float-end">4.5/5</span>
        </div>
      </div>
    </router-link>
    `,
    props: ["searchResult"],
})