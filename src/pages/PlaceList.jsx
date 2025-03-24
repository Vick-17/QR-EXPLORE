import { useState, useEffect } from "react";
import "../Style/pages/PlaceList.css";
import { get } from "../service/BackendService";

const PlaceList = () => {
    const [searchTerm, setSearchTerm] = useState("");
    const [currentPage, setCurrentPage] = useState(0);
    const [places, setPlaces] = useState([]);
    const [totalPages, setTotalPages] = useState(0);
    const [isLoading, setIsLoading] = useState(true);

    const getPlaces = async (page = 0) => {
        try {
            setIsLoading(true);
            const response = await get(`/places?page=${page}&size=6`);
            setPlaces(response.content);
            setTotalPages(response.totalPages);
            setIsLoading(false);
        } catch (e) {
            console.error("Une erreur s'est produite lors de la récupération des lieux", e);
            setIsLoading(false);
        }
    };

    useEffect(() => {
        getPlaces(currentPage);
    }, [currentPage]);

    const handleSearch = (e) => {
        setSearchTerm(e.target.value);
        setCurrentPage(0);
    };

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    const filteredPlaces = places.filter((place) =>
        place.name.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="container">
            <h1 className="title">Explore Places</h1>
            <div className="search-container">
                <input
                    type="text"
                    placeholder="Search for a place..."
                    value={searchTerm}
                    onChange={handleSearch}
                    className="search-bar"
                />
            </div>
            <div className="places-grid">
                {isLoading ? (
                    <p>Loading...</p>
                ) : (
                    filteredPlaces.map((place) => (
                        <div className="place-card" key={place.id}>
                            <div className="place-details">
                                <h2 className="place-title">{place.name}</h2>
                                <p className="place-description">{place.description}</p>
                                <div className="place-actions">
                                    <button className="btn btn-primary">
                                        <i className="icon-heart"></i> Save to Favorites
                                    </button>
                                    <button className="btn btn-secondary">
                                        <i className="icon-clock"></i> Save for Later
                                    </button>
                                </div>
                            </div>
                        </div>
                    ))
                )}
            </div>
            <div className="pagination">
                {[...Array(totalPages).keys()].map((page) => (
                    <button
                        key={page}
                        className={`pagination-item ${page === currentPage ? "active" : ""}`}
                        onClick={() => handlePageChange(page)}
                    >
                        {page + 1}
                    </button>
                ))}
            </div>
        </div>
    );
};

export default PlaceList;
