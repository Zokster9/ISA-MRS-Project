<template>
    <div v-if="adventure" style="margin: 100px">
        <div class="d-flex flex-row" style="margin: 50px">
            <div class="d-flex flex-column" style="width: 50%">
                <div class="slideshow-container">
					<div class="mySlides" style="display: block;">
						<div class="numbertext">{{this.currentPicture + 1}} / {{adventure.pictures.length}}</div>
						<img :src="require(`../assets/${this.adventure.pictures[this.currentPicture]}`)" style="width:100%; height:400px; border-radius:25px">
					</div>
					<a class="prev" @click="changePicture(-1)">&#10094;</a>
					<a class="next" @click="changePicture(1)">&#10095;</a>
                </div>
				<div class="d-flex flex-column" style="border-radius: 25px; margin: 5px; border: 1px solid #323539">
					<div>
                        <h5 style="margin: 5px">Rules of conduct</h5>
                        <ul>
                            <template v-for="rule in adventure.rulesOfConduct">
                                <li style="margin: 5px" :key="rule">{{rule}}</li>
                            </template>
                        </ul>
                    </div>
                    <div>
                        <h5 style="margin: 5px">Fishing equipment</h5>
                        <ul>
                            <template v-for="equipment in adventure.fishingEquipment">
                                <li style="margin: 5px" :key="equipment">{{equipment}}</li>
                            </template>
                        </ul>
                    </div>
				</div>
            </div>
            <div class="d-flex flex-column" style="width: 50%; margin: 5px">
                <div style="height: 10%; margin: 5px">
                    <h1>
                        <span>{{adventure.name}}</span>
                    </h1>
                </div>
                <div style="height: 80%; margin: 5px; border: 1px solid #323539; border-radius: 25px; ">
                    <div>
                        <h5 style="margin: 5px">Description</h5>
                        <p style="margin: 5px">{{adventure.description}}</p>
                    </div>
                    <div>
                        <h5 style="margin: 5px">Reservation cancellation conditions</h5>
						<p style="margin: 5px">{{adventure.reservationCancellationConditions}}</p>
                    </div>
                    <div>
                        <h5 style="margin: 5px">Address</h5>
                        <p style="margin: 5px">{{adventure.country}}, {{adventure.city}}, {{adventure.street}}</p>
                    </div>
					<div>
						<iframe :src="mapSrc" style="margin: 15px; border-radius: 25px; border: 1px solid #323539"></iframe>
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
</template>
<script>
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)
    export default {
        name: "AdventurePage",
        data() {
            return {
                adventure: null,
                currentPicture: 0,
                mapSrc: ""
            }
        },
        methods: {
            changePicture(n) {
				if (this.currentPicture + n < 0) {
					this.currentPicture = this.adventure.pictures.length - 1;
				}else if (this.currentPicture + n > this.adventure.pictures.length - 1) {
					this.currentPicture = 0;
				}else {
					this.currentPicture += n;
				}
			}
        },
        mounted () {
            axios.get("http://localhost:8088/adventures/getAdventure/" + this.$route.params.id).then((response) => {
                this.adventure = response.data;
                this.mapSrc = "https://maps.google.com/maps?q=" + response.data.country + "," + response.data.city + "," + response.data.street + "&t=&z=13&ie=UTF8&iwloc=&output=embed";
            });
        }
    }
</script>