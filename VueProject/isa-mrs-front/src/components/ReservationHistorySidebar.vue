<template>
    <div class="shadow-lg rounded" style="flex-shrink:0">
        <div style="width:300px;height:100%;">
            <form @submit.prevent style="padding:10px;padding-top:100px">
                <div class="form-group">
                    <label>Sort by</label><br>
                    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                      <input @click="sort" type="radio" class="btn-check" id="btnradio1" autocomplete="off" value="price" v-model="sortBy" checked>
                      <label class="btn btn-outline-primary" for="btnradio1">Price</label>
                    
                      <input @click="sort" type="radio" class="btn-check" id="btnradio2" autocomplete="off" value="date" v-model="sortBy">
                      <label class="btn btn-outline-primary" for="btnradio2">Date</label>
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        data ()  {
            return {
                sortBy: "price",
            }
        },
        methods: {
            async sort() {
                await setTimeout(() => {
                    this.$emit("sort", this.sortBy)
                }, 100)
            }
        },
    }
</script>