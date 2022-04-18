Vue.component("search-sidebar", {
    template: `
    <div class="shadow-lg rounded" style="flex-shrink:0">
        <div style="width:300px;height:100%;">
            <form @submit.prevent style="padding:10px;padding-top:100px">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input v-model="form.name" id="name" type="text" pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                </div>
                <div class="form-group">
                    <label for="address">Address</label>
                    <input v-model="form.address" id="address" type="text" pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                </div>
                <div class="form-group">
                    <label for="city">City</label>
                    <input v-model="form.city" id="city" type="text" pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                </div>
                <div class="form-group">
                    <label for="country">Country</label>
                    <input v-model="form.country" id="country" type="text" pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                </div>
                <div class="form-group">
                    <label for="description">Promotional description</label>
                    <input v-model="form.description" id="description" type="text" pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                </div>
                <div class="form-group">
                    <label for="averageRating">Average rating</label>
                    <input v-model="form.averageRating" id="averageRating" type="text" pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                </div>
                <div class="form-group">
                    <button @click="search" type="submit" class="btn btn-primary btn-lg btn-block">Search</button>
                </div>
                <div class="form-group">
                    <label>Sort by</label><br>
                    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                      <input type="radio" class="btn-check" id="btnradio1" autocomplete="off" value="name" v-model="sortBy" checked>
                      <label class="btn btn-outline-primary" for="btnradio1">Name</label>
                    
                      <input type="radio" class="btn-check" id="btnradio2" autocomplete="off" value="averageRating" v-model="sortBy">
                      <label class="btn btn-outline-primary" for="btnradio2">Average rating</label>
                    </div>
                </div>
            </form>
        </div>
    </div>
    `,
    props: ["results"],
    data() {
        return {
            sortBy: "name",
            form: {
                name: "",
                address: "",
                city: "",
                country: "",
                description: "",
                averageRating: "",
            },
        }
    },
    computed: {
        orderedResults() {
            return _.orderBy(this.results, this.sortBy)
        }
    },
})