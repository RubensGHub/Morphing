\LoadClass[a4paper,12pt]{article} % Classe du document

%--------------------- Packages ------------------------

\RequirePackage[french]{babel} %Langue du document
\RequirePackage[utf8]{inputenc} %Caractères spéciaux
\RequirePackage[section]{placeins}%Pour placement de section
\RequirePackage[T1]{fontenc} %Quelques lettres qui sont pas inclus dans UTF-8
\RequirePackage{mathtools} %Paquet pour des équations et symboles mathématiques
\RequirePackage{siunitx} %Pour écrire avec la notation scientifique (Ex.: \num{2e+9})
\RequirePackage{float} %Pour placement d'images
\RequirePackage{graphicx} %Paquet pour insérer des images
\RequirePackage[justification=centering]{caption} %Pour les légendes centralisées
\RequirePackage{subcaption}
\RequirePackage{wallpaper}
\RequirePackage{nomencl}
\makenomenclature
\RequirePackage{fancyhdr}
\pagestyle{fancy}
\fancyheadoffset{1cm}
\setlength{\headheight}{2cm}
\RequirePackage{url}
\RequirePackage[hidelinks]{hyperref}%Paquet pour insérer légendes dans des sous-figures comme Figure 1a, 1b
\RequirePackage[left=3cm,right=3cm,top=3cm,bottom=2cm]{geometry} %Configuration de la page
\RequirePackage[most]{tcolorbox}
\RequirePackage{xcolor}
\RequirePackage{amsfonts}
\RequirePackage{minted}
%-------------------- Informations sur le rapport ----------------------

\newcommand{\mention}[1]{\renewcommand{\mention}{#1}}
\newcommand{\trigrammemention}[1]{\renewcommand{\trigrammemention}{#1}}
\newcommand{\master}[1]{\renewcommand{\master}{#1}}
\newcommand{\filiere}[1]{\renewcommand{\filiere}{#1}}

\newcommand{\titre}[1]{\renewcommand{\titre}{#1}}

\newcommand{\eleve}[1]{\renewcommand{\eleve}{#1}}

\newcommand{\tuteurecole}[1]{\renewcommand{\tuteurecole}{#1}}
\newcommand{\tuteurentreprise}[1]{\renewcommand{\tuteurentreprise}{#1}}

\newcommand{\logoentreprise}[1]{\renewcommand{\logoentreprise}{#1}}

\newcommand{\dates}[1]{\renewcommand{\dates}{#1}}

\newcommand{\fairemarges}{
\makenomenclature
\pagestyle{fancy}
\fancyheadoffset{0.5cm}


\lhead{\includegraphics[scale=0.2]{logos/CSxParisSaclay.jpg}} %Affichage de l'image au top de la page
\rhead{\nouppercase{\leftmark}}
\rfoot{\thepage}
\cfoot{\textbf{\titre}}
\lfoot{\trigrammemention}

}

\newcommand{\fairepagedegarde}{
\begin{titlepage}

\ThisLLCornerWallPaper{0.6}{logos/bordureCS.png}

\centering %Centraliser le contenu

% Logo Entreprise x ecole

\begin{figure}
    \begin{subfigure}{.5\textwidth}
        %\centering
        \includegraphics[width=1\textwidth]{\logoentreprise}\par\vspace{1cm}
    \end{subfigure}%
    \begin{subfigure}{.5\textwidth}
        %\centering
        \hfill
        \includegraphics[width=0.6\textwidth]{logos/logoCS.png}\par\vspace{1cm} %Insertion du logo Ecole
    \end{subfigure}
\end{figure}

% Titre du rapport
\rule{\linewidth}{0.2 mm} \\[0.4 cm]
{\huge\bfseries \titre \par} \
\rule{\linewidth}{0.2 mm} \\[1.0 cm]

% Nom de l'étudiant
{\scshape\large \eleve \par} 
\vspace{1.0cm}

% Parcours de l'étudiant
{\scshape \mention \\ \filiere \\ \master \par} 
\vspace{1cm}

% Dates
{\large \dates \par} 

\vspace{6cm}

% Représentants de l'étudiant
%\begin{minipage}{0.5\textwidth}
%	\begin{flushleft} 
%	\emph{\textbf{Tuteurs école :}}\\ 
%    \tuteurecole \\ 
%	\end{flushleft}
%\end{minipage}
%~
\hspace*{8cm}
\begin{minipage}{0.4\textwidth}
	\begin{flushright} 
	\emph{\textbf{Tuteur entreprise :}} \\
	 \tuteurentreprise \\
	\end{flushright}
\end{minipage}\\[4cm]
    
	\vfill
	

\end{titlepage}
\newpage
}

\newcommand{\tabledematieres}{
%\vspace*{5cm}
\tableofcontents
\newpage
}

\newcommand{\insererfigure}[4]{
\begin{figure}[ht]
\centering
\includegraphics[height=#2]{#1}
\caption{#3}
\label{fig: #4}
\end{figure}
}

