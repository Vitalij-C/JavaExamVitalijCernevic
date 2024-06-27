const Footer = () => {
    const year = new Date().getFullYear();

    return <>
        <footer id="footer">
            <div>
                <div>{year}</div>
                <div>Vitalij Černevič</div>
            </div>
        </footer>
    </>
}

export default Footer;