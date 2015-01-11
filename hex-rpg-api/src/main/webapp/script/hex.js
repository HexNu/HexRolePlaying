hex = {
    Campaign: {
        Form: function (id) {
            if (id !== null && id !== undefined) {
                this.id = id;
                hex.request.fetchCampaign(id);
            }
        }
    },
    Story: {
        Form: function (id) {
            if (id !== null && id !== undefined) {
                this.id = id;
                hex.request.fetchStory(id);
            }
        }
    },
    Episode: {
        Form: function (id) {
            if (id !== null && id !== undefined) {
                this.id = id;
                hex.request.fetchEpisode(id);
            }
        }
    },
    Character: {
        Form: function (id) {
            if (id !== null && id !== undefined) {
                this.id = id;
                hex.request.fetchCharacter(id);
            }
        }
    },
    NonPlayingCharacter: {
        Form: function (id) {
            if (id !== null && id !== undefined) {
                this.id = id;
                hex.request.fetchNonPlayingCharacter(id);
            }
        }
    },
    request: {
        fetchCampaign: function (id) {

        },
        fetchStory: function (id) {

        },
        fetchEpisode: function (id) {

        },
        fetchCharacter: function (id) {

        },
        fetchNonPlayingCharacter: function (id) {

        },
        JsonHTTPRequest: function (url, jsonData, method, action, async) {
            var jsonString = JSON.stringify(jsonData);
            var httpRequest = new XMLHttpRequest();
            httpRequest.setRequestHeader("Content-Type", "application/json");
            httpRequest.setRequestHeader("Content-Length", jsonString.length);
            httpRequest.onreadystatechange = function () {
                if (httpRequest.readyState === 4 && httpRequest.status === 200) {
                    alert(httpRequest.responseText);
                    if (action !== undefined && action !== null) {
                        action(JSON.parse(httpRequest.responseText));
                    }
                }
            };
            httpRequest.open(method, url, async || true);
            httpRequest.send(jsonString);
        }
    }
};