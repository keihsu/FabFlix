import Socket from "../util/Socket";
import { movieEPs } from "../Config.json";

const { movieSearchEP, movieBrowseEP, movieGetEP } = movieEPs;

async function movieSearch(path) {
    const p = movieSearchEP + path;
    return await Socket.GET(p);
}

async function movieBrowse(path) {
    const p = movieBrowseEP + path;
    console.log(p);
    return await Socket.GET(p);
}

async function movieGet(path) {
    const p = movieGetEP + path;
    console.log(p);
    return await Socket.GET(p);
}
export default { movieSearch, movieBrowse, movieGet };