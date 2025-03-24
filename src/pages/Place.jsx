import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import "../Style/pages/Place.css";
import { get, postFile } from "../service/BackendService";

const Place = () => {
    const { id } = useParams();
    const [place, setPlace] = useState(null);
    const [comments, setComments] = useState([]);
    const [newComment, setNewComment] = useState("");
    const [newPhoto, setNewPhoto] = useState(null);
    const [isLoading, setIsLoading] = useState(true);

    const fetchPlaceDetails = async () => {
        try {
            setIsLoading(true);
            const placeData = await get(`/places/${id}`);

            setPlace(placeData);

            const commentsData = await get(`/comments?placeId=${id}`);

            const commentWithImageUrl = commentsData.content.map((comment) => ({
                ...comment,
                imageName: `http://localhost:8080/api/comments/getImage/${comment.id}`,
            }));
            setComments(commentWithImageUrl);
            
            setIsLoading(false);
        } catch (e) {
            console.error("Error fetching place details", e);
            setIsLoading(false);
        }
    };

    const handleCommentSubmit = async (e) => {
        e.preventDefault();
        if (!newComment) return;

        const formData = new FormData();
        formData.append("description", newComment);
        if (newPhoto) {
            formData.append("picture", newPhoto);
        }

        try {
            const response = await postFile(`/comments/postCommentByPlace?placeId=${id}`, formData);
            const newCommentWithImage = {
                ...response,
                imageName: `http://localhost:8080/api/comments/getImage/${response.id}`,
            };
            setComments((prevComments) => [...prevComments, newCommentWithImage]);
            setNewComment("");
            setNewPhoto(null);
        } catch (e) {
            console.error("Error posting comment", e);
        }
    };

    useEffect(() => {
        fetchPlaceDetails();
    }, [id]);

    if (isLoading) {
        return <p>Loading...</p>;
    }

    return (
        <div className="place-container">
            {place && (
                <>
                    <div className="place-details">
                        <h1 className="place-title">{place.name}</h1>
                        <p className="place-description">{place.description}</p>
                        {place.imageUrl && (
                            <img
                                src={place.imageUrl}
                                alt={place.name}
                                className="place-image"
                            />
                        )}
                    </div>
                    <div className="comments-section">
                        <h2>Commentaires</h2>
                        <form onSubmit={handleCommentSubmit} className="comment-form">
                            <textarea
                                placeholder="Ecrit ton commentaire ici..."
                                value={newComment}
                                onChange={(e) => setNewComment(e.target.value)}
                                className="comment-input"
                            ></textarea>
                            <input
                                type="file"
                                accept="image/*"
                                onChange={(e) => setNewPhoto(e.target.files[0])}
                                className="photo-input"
                            />
                            <button type="submit" className="btn btn-primary">
                                Partager
                            </button>
                        </form>
                        <div className="comments-list">
                            {comments.map((comment) => (
                                <div className="comment-card" key={comment.id}>
                                    {comment.imageName && comment.imageName.trim() !== "" && (
                                        <img
                                            src={comment.imageName}
                                            alt="Comment"
                                            className="comment-photo"
                                            onError={(e) => {
                                                e.target.style.display = "none";
                                            }}
                                        />
                                    )}
                                    <p className="comment-text">{comment.description}</p>
                                    <p className="comment-author">
                                        Ecrit par {comment.user.username}
                                    </p>
                                </div>
                            ))}
                        </div>
                    </div>
                </>
            )}
        </div>
    );
};

export default Place;