Vue.component("retreat-page", {
    template:`
        <div style="margin: 100px">
        <div class="d-flex flex-row" style="margin: 50px">
            <div class="d-flex flex-column" style="width: 50%">
                <div class="d-flex" style="margin: 10px">
                    <div style="width: 100%; border: 1px solid #323539;">
                        <img :src="'/images/'+retreat.pictures[0]" width="100%">
                    </div> 
                </div>
                <div class="d-flex flex-row">
                    <template v-for="picture in retreat.pictures">
                        <div style="margin: 10px">
                            <img :id="picture" :src="'/images/'+picture" style="width: 89px; height: 89px">
                        </div>
                    </template>
                </div>
            </div>
            <div class="d-flex flex-column" style="width: 50%; margin: 5px">
                <div style="height: 10%; margin: 5px">
                    <h1>
                        <span>{{retreat.name}}</span>
                    </h1>
                </div>
                <div style="height: 80%; margin: 5px; border: 1px solid #323539">
                    <div>
                        <h5 style="margin: 5px">Description</h5>
                        <p style="margin: 5px">{{retreat.description}}</p>
                    </div>
                    <div>
                        <h5 style="margin: 5px">Rules of conduct</h5>
                        <ul>
                            <template v-for="rule in retreat.rulesOfConduct">
                                <li style="margin: 5px">{{rule}}</li>
                            </template>
                        </ul>
                    </div>
                    <div>
                        <h5 style="margin: 5px">Additional services</h5>
                        <ul>
                            <template v-for="service in retreat.additionalServices">
                                <li style="margin: 5px">{{service}}</li>
                            </template>
                        </ul>
                    </div>
                    <div class="d-flex flex-row">
                        <h5 style="margin: 5px">Number of rooms: {{retreat.numOfRooms}}</h5>
                    </div>
                    <div class="d-flex flex-row">
                        <h5 style="margin: 5px">Number of beds: {{retreat.numOfBeds}}</h5>
                    </div>
                    <div>
                        <h5 style="margin: 5px">Address</h5>
                        <p style="margin: 5px">{{retreat.country}}, {{retreat.city}}, {{retreat.street}}</p>
                    </div>
                </div>
                <div class="d-flex flex-row" style="height: 10%; margin: 5px; border: 1px solid #323539">
                    <div class="d-flex flex-row" style="margin: 5px; width: 66%">
                        <div class="d-flex flex-row" style="margin: 5px; width: 50%">
                            <label style="margin: 5px">Date from: </label>
                            <input type="date" />
                        </div> 
                        <div class="d-flex flex-row" style="margin: 5px; width: 50%">
                            <label style="margin: 5px">Date to: </label>
                            <input type="date" />
                        </div>    
                    </div>
                    <div style="margin: 5px; width: 33%">
                        <button class="btn btn-primary" value="Reserve">Make reservation</button>
                    </div>
                </div>
            </div>
        </div>
        </div>
    `,
    data(){
        return {
            retreat: null
        }
    },

    methods: {

    },

    mounted(){
        axios.get("/retreats/" + this.$route.params.id).then((response) => {
            this.retreat = response.data;
        })
    },
})