<template>
    <form class="w-50 mx-auto mt-5">
            <h2> New retreat </h2>
                <div class="form-group mb-3">
                    <label>Name</label>
                    <input v-model="form.name" type="text" class="form-control"/>
                    <p v-if="!nameIsValid" class="alert alert-danger">Name is required.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Description</label>
                    <textarea v-model="form.description" type="text" class="form-control"/>
                    <p v-if="!descriptionIsValid" class="alert alert-danger">Description is required.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Country</label>
                    <input v-model="form.country" type="text" class="form-control"/>
                    <p v-if="!countryIsValid" class="alert alert-danger">Country is required, and it starts with first capital letter.</p>
                </div>
                <div class="form-group mb-3">
                    <label>City</label>
                    <input v-model="form.city" type="text" class="form-control"/>
                    <p v-if="!cityIsValid" class="alert alert-danger">City is required, and it starts with first capital letter.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Address</label>
                    <input v-model="form.address" type="text" class="form-control"/>
                    <p v-if="!addressIsValid" class="alert alert-danger">Address is required.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Number of rooms</label>
                    <input v-model.number="form.numOfRooms" type="number" class="form-control"/>
                    <p v-if="!roomsIsValid" class="alert alert-danger">Number of rooms is required, and it must be number greater than 0.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Number of beds</label>
                    <input v-model.number="form.numOfBeds" type="number" class="form-control"/>
                    <p v-if="!bedsIsValid" class="alert alert-danger">Number of beds is required, and it must be number greater than 0.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Rules of conduction:</label>
                    <br>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Inside smoking disallowed"/>
                        <label>Inside smoking disallowed</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Pet friendly"/>
                        <label>Pet friendly</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Parties disallowed"/>
                        <label>Parties disallowed</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Furniture braking is charged extra"/>
                        <label>Furniture braking is charged extra</label>
                    </div>
                </div>
                <div class="form-group mb-3">
                    <label>Additional services:</label>
                    <br>
                    <template v-for="tag in tags">
                        <div class="form-group mb-3" :key="tag">
                            <input type="checkbox" v-model="form.additionalServices" :value="tag.name" :key="tag.id"/>
                            <label :for="tag.key">{{tag.name}}</label>
                        </div>
                    </template>
                </div>
                <div class="form-group mb-3">
                    <label>Pictures:</label>
                    <input accept="image/*" type="file" class="form-control" @change="addPicture($event)" multiple/>
                </div>
                <div class="form-group mb-3">
                    <button @click="addRetreat" type="submit" class="btn btn-primary float-end">Add retreat</button>
                </div>
        </form>
</template>

<script>
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        name: "AddRetreat",
        data() {
            return {
                form: {
                    name: null,
                    description: null,
                    country: null,
                    city: null,
                    address: null,
                    numOfRooms: null,
                    numOfBeds: null,
                    rulesOfConduction: [],
                    additionalServices: [],
                    pictures: []
                },
                tags: [],
                retVal: null
            }
        },
        computed: {
            nameIsValid() {
                return !!this.form.name;
            },
            descriptionIsValid() {
                return !!this.form.description;
            },
            countryIsValid() {
                return RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(this.form.country) && !!this.form.country;
            },
            cityIsValid() {
                return RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(this.form.city) && !!this.form.city;
            },
            addressIsValid() {
                return !!this.form.address;
            },
            roomsIsValid() {
                return typeof this.form.numOfRooms === 'number' && !!this.form.numOfRooms && this.form.numOfRooms > 0;
            },
            bedsIsValid() {
                return typeof this.form.numOfBeds === 'number' && !!this.form.numOfBeds && this.form.numOfBeds > 0;
            },
            formIsValid() {
                return this.nameIsValid && this.descriptionIsValid && this.countryIsValid && this.cityIsValid && this.addressIsValid && this.roomsIsValid && this.bedsIsValid;
            }
        },
        methods: {
            addRetreat() {
                if (this.formIsValid) {
                    this.sendData();
                }
            },
            sendData() {
                axios.post("http://localhost:8088/retreats/create-retreat", {
                    name: this.form.name,
                    description: this.form.description,
                    country: this.form.country,
                    city: this.form.city,
                    street: this.form.address,
                    rulesOfConduct: this.form.rulesOfConduction,
                    pictures: this.form.pictures,
                    numOfRooms: this.form.numOfRooms,
                    numOfBeds: this.form.numOfBeds,
                    additionalServices: this.form.additionalServices
                }).then((response) => {
                    this.retVal = response.data;
                    alert("Added retreat");
                }).catch(() => {
                    alert("ne valja brt");
                });
            },

            addPicture(e) {
                let files = e.target.files || e.dataTransfer.files;
                if (!files.length)
                    return;
                for (let file of files) this.form.pictures.push(file.name);
            }
        },

        mounted() {
            axios.get("http://localhost:8088/tags").then((response) => {this.tags = response.data});
        }
    }
</script>